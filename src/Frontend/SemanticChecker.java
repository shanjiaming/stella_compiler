package Frontend;

import AST.*;
import Backend.IRBuilder;
import IR.PointerRegister;
import Util.*;
import Util.MxError.SemanticError;

// in this part, we also new all the entity we wanted.

public class SemanticChecker extends ASTVisitor {
    private Scope currentScope;
    private ClassDef currentClass;
    private FuncDef currentFunc;
    private ConstructDef currentConstructDef;
    private ReturnStmt currentReturnStmt;

    private int s0Const = -IRBuilder.STACKSTARTSIZE;
    private int previouss0;
    private int programframesize = IRBuilder.STACKSTARTSIZE + 4;
    private void assignaddress(PointerRegister p){
        if (!p.isGlobal) {
        p.address = (s0Const -= 4);
        if(currentFunc != null) currentFunc.frameSize += 4;
        else programframesize += 4;
        }
    }


    @Override
    public void visit(Program it) {
        currentScope = new Scope(currentScope);
        position pos = position.INLINE_POS;
        final FuncDef[] inlineFuncDefs = new FuncDef[]{
                new FuncDef(pos, "print", null, new Type[]{Type.STRING_TYPE}, new String[]{"str"}),
                new FuncDef(pos, "println", null, new Type[]{Type.STRING_TYPE}, new String[]{"str"}),
                new FuncDef(pos, "printInt", null, new Type[]{Type.INT_TYPE}, new String[]{"n"}),
                new FuncDef(pos, "printlnInt", null, new Type[]{Type.INT_TYPE}, new String[]{"n"}),
                new FuncDef(pos, "getString", Type.STRING_TYPE),
                new FuncDef(pos, "getInt", Type.INT_TYPE),
                new FuncDef(pos, "toString", Type.STRING_TYPE, new Type[]{Type.INT_TYPE}, new String[]{"i"})
        };
        for (FuncDef funcDef : inlineFuncDefs) Type.addFuncType(funcDef, pos);

        Type.STRING_TYPE.getClassDef().funcDefs.values().forEach(f -> {
            f.parameterTypes.add(Type.STRING_TYPE);
            f.parameterIdentifiers.add("this");
        });

        for (ProgramUnit programUnit : it.programUnits) {
            ((ASTNode) programUnit).accept(this);
        }
        FuncDef mainFunc = Type.getFuncDef("main");
        if (mainFunc == null) throw new SemanticError("no main function", it.pos);
        if (!Type.INT_TYPE.equals(mainFunc.returnType))
            throw new SemanticError("main function return type not int", it.pos);
        if (!mainFunc.parameterIdentifiers.isEmpty() || !mainFunc.parameterTypes.isEmpty())
            throw new SemanticError("main function should not hava parameters", it.pos);
        currentScope = currentScope.parentScope();
        it.globalframesize = programframesize;
    }

    @Override
    public void visit(FuncDef it) {
//        if (currentClass != null) {
//            it.parameterTypes.add(Type.stringToType(currentClass.name));
//            it.parameterIdentifiers.add("this");
//        }
        previouss0 = s0Const;
        s0Const = -IRBuilder.STACKSTARTSIZE;
        int sz = it.parameterTypes.size();
        if (sz != it.parameterIdentifiers.size())
            throw new SemanticError("function parameters and identifiers numbers do not match", it.pos);
        currentScope = new Scope(currentScope);
        for (int i = 0; i < sz; ++i) {
            currentScope.defineVariable(it.parameterIdentifiers.get(i), it.parameterTypes.get(i), it.pos);
            it.vars.add(new PointerRegister());
            currentScope.addPointerRegister(it.parameterIdentifiers.get(i), it.vars.get(i));
        }
        currentFunc = it;
        for (var para : it.vars){
            assignaddress(para);
        }
        ((ASTNode) it.body).accept(this);
        if (!"main".equals(it.name) && it.returnType != null && currentReturnStmt == null)
            throw new SemanticError("no return statement in non void function", it.pos);
        currentReturnStmt = null;
        currentFunc = null;
        currentScope = currentScope.parentScope();
        s0Const = previouss0;
    }

    @Override
    public void visit(ClassDef it) {
        currentScope = new Scope(currentScope);
        currentClass = it;
        it.members.forEach((str, type) -> {
            currentScope.defineVariable(str, type, it.pos);
            it.offsets.put(str, it.classSize);
            it.classSize += 32;
        });
        it.constructDef.accept(this);
        it.funcDefs.values().forEach(funcDef -> funcDef.accept(this));
        currentClass = null;
        currentScope = currentScope.parentScope();
    }

    @Override
    public void visit(SuiteStmt it) {
        currentScope = new Scope(currentScope);
        it.stmts.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope();
    }

    @Override
    public void visit(VarDefStmt it) {
        int sz = it.names.size();
        for (int i = 0; i < sz; ++i) {
            Expr expr = it.init.get(i);
            if (expr != null) {
                expr.accept(this);
                if (!it.varType.equals(expr.type)) throw new SemanticError("initialize type not match", it.pos);
            }
            currentScope.defineVariable(it.names.get(i), it.varType, it.pos);
            PointerRegister p;
            if (it.isGlobal) p = new PointerRegister(it.names.get(i), true);
            else p = new PointerRegister();
            it.vars.add(p);
            currentScope.addPointerRegister(it.names.get(i), it.vars.get(i));
            assignaddress(p);
        }
    }

    private ConstructDef isvisiting;
    @Override
    public void visit(ConstructDef it) {
        if(isvisiting == it) return;
        isvisiting = it;
        assert (currentClass != null);
        it.proxyFunc = new FuncDef(it.pos, currentClass.name, it.body, null);
        it.proxyFunc.parameterIdentifiers.add("this");
        it.proxyFunc.parameterTypes.add(Type.stringToType(currentClass.name));
        it.proxyFunc.accept(this);

//        currentScope = new Scope(currentScope);
//        currentConstructDef = it;
//        it.body.accept(this);
//        currentConstructDef = null;
//        currentScope = currentScope.parentScope();
    }

    @Override
    public void visit(BinaryExpr it) {
        boolean intintReturnint = "*".equals(it.op) || "/".equals(it.op) || "%".equals(it.op) || "+".equals(it.op) || "-".equals(it.op) || "<<".equals(it.op) || ">>".equals(it.op) || "&".equals(it.op) || "^".equals(it.op) || "|".equals(it.op);
        boolean compareReturnbool = "<".equals(it.op) || ">".equals(it.op) || "<=".equals(it.op) || ">=".equals(it.op) || "==".equals(it.op) || "!=".equals(it.op);
        boolean boolboolReturnbool = "==".equals(it.op) || "!=".equals(it.op) || "&&".equals(it.op) || "||".equals(it.op);
        it.lhs.accept(this);
        it.rhs.accept(this);
        Type ltype = it.lhs.type;
        if (!ltype.equals(it.rhs.type)) throw new SemanticError("types not match", it.pos);
        if (Type.INT_TYPE.equals(ltype)) {
            if (intintReturnint) it.type = Type.INT_TYPE;
            else if (compareReturnbool) it.type = Type.BOOL_TYPE;
            else throw new SemanticError("types not match", it.pos);
        } else if (Type.BOOL_TYPE.equals(ltype)) {
            if (boolboolReturnbool) it.type = Type.BOOL_TYPE;
            else throw new SemanticError("types not match", it.pos);
        } else if (Type.STRING_TYPE.equals(ltype)) {
            if ("+".equals(it.op)) it.type = Type.STRING_TYPE;
            else if (compareReturnbool) it.type = Type.BOOL_TYPE;
            else throw new SemanticError("types not match", it.pos);
        } else if ("==".equals(it.op) || "!=".equals(it.op)) {
            it.type = Type.BOOL_TYPE;
        } else throw new SemanticError("types not match", it.pos);
        assignaddress(it.pointerRegister);
    }

    @Override
    public void visit(UnaryExpr it) {
        it.lhs.accept(this);
        if (!(
                (it.lhs.type.equals(Type.INT_TYPE) &&
                        (it.op.equals("+") || it.op.equals("-") || it.op.equals("~"))
                )
                        ||
                        (it.lhs.type.equals(Type.BOOL_TYPE) && it.op.equals("!"))
        ))
            throw new SemanticError("types not match", it.pos);
        it.type = it.lhs.type;
        assignaddress(it.pointerRegister);
    }

    @Override
    public void visit(PrefixExpr it) {
        it.lhs.accept(this);
        if (!it.isAssignable) throw new SemanticError("prefix not assignable", it.pos);
        if (!it.lhs.type.equals(Type.INT_TYPE)) throw new SemanticError("types not match", it.pos);
        it.type = it.lhs.type;
        it.pointerRegister = it.lhs.pointerRegister;//TODO 不很会搞，以及下标也不很会搞 // 移到IRbuilder加一条语句好了
    }

    @Override
    public void visit(SuffixExpr it) {
        it.lhs.accept(this);
        if (!it.lhs.isAssignable) throw new SemanticError("suffix not assignable", it.pos);
        if (!it.lhs.type.equals(Type.INT_TYPE)) throw new SemanticError("types not match", it.pos);
        it.type = it.lhs.type;
        assignaddress(it.pointerRegister);
    }

    @Override
    public void visit(IfStmt it) {
        it.cond.accept(this);
        if (!Type.BOOL_TYPE.equals(it.cond.type)) throw new SemanticError("should be bool in if condition", it.pos);
        currentScope = new Scope(currentScope);
        it.trueNode.accept(this);
        currentScope = currentScope.parentScope();
        if (it.falseNode != null) {
            currentScope = new Scope(currentScope);
            it.falseNode.accept(this);
            currentScope = currentScope.parentScope();
        }
    }

    @Override
    public void visit(ThisExpr it) {
        if (currentClass == null) {
            throw new SemanticError("this statement outside class", it.pos);
        }
        it.type = Type.stringToType(currentClass.name);
        it.pointerRegister = currentScope.getPointerRegister("this", true);
        if(it.pointerRegister == null){
            System.out.println("not find this");
        }
    }

    @Override
    public void visit(NewArrayExpr it) {
        it.dims.forEach(index -> {
            index.accept(this);
            if (!Type.INT_TYPE.equals(index.type))
                throw new SemanticError("should use int to index, but use " + it.type, it.pos);
        });
        assignaddress(it.pointerRegister);
        assignaddress(it.tempPointer);
    }

    @Override
    public void visit(ForStmt it) {
        currentScope = new Scope(currentScope, it);
        if (it.init != null) it.init.accept(this);
        if (it.cond != null) {
            it.cond.accept(this);
            if (!Type.BOOL_TYPE.equals(it.cond.type))
                throw new SemanticError("should be bool in loop condition", it.pos);
        }
        if (it.incr != null) it.incr.accept(this);
        if (it.body != null) it.body.accept(this);
        currentScope = currentScope.parentScope();
    }

    @Override
    public void visit(ContinueStmt it) {
        if (currentScope.getForStmt() == null) throw new SemanticError("continue statement not in loop scope", it.pos);
    }

    @Override
    public void visit(BreakStmt it) {
        if (currentScope.getForStmt() == null) throw new SemanticError("break statement not in loop scope", it.pos);
    }

    @Override
    public void visit(ReturnStmt it) {
        LambdaExpr currentLambdaExpr = currentScope.getLambdaExpr();
        if (currentFunc == null && currentLambdaExpr == null && currentConstructDef == null) {
            throw new SemanticError("return statement out of function", it.pos);
        }
        if (it.returnExpr != null) it.returnExpr.accept(this);
        if (currentLambdaExpr != null) currentLambdaExpr.type = (it.returnExpr == null) ? null : it.returnExpr.type;
        else if (currentFunc != null) {
            if (it.returnExpr == null && currentFunc.returnType != null ||
                    it.returnExpr != null && currentFunc.returnType == null ||
                    currentFunc.returnType != null && !currentFunc.returnType.equals(it.returnExpr.type))
                throw new SemanticError("return type does not match with function declaration", it.pos);
            currentReturnStmt = it;
        } else if (currentConstructDef != null) {
            if (it.returnExpr != null)
                throw new SemanticError("return type should be void in construct function", it.pos);
        }
    }

    @Override
    public void visit(IndexExpr it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type.dim <= 0) throw new SemanticError(it.lhs.type + "cannot be indexed", it.pos);
        if (!Type.INT_TYPE.equals(it.rhs.type))
            throw new SemanticError("should use int to index, but use " + it.type, it.pos);
        it.type = it.lhs.type.reduceDim();
        assignaddress(it.pointerRegister);
        assignaddress(it.addressPointer);
        assignaddress(it.tempPointer);
    }

    @Override
    public void visit(MemberExpr it) {
        it.lhs.accept(this);
        ClassDef classDef = it.lhs.type.getClassDef();
        Type type = classDef.members.get(it.rhs.name);
        if (type == null) throw new SemanticError("no this name member variable", it.pos);
        it.type = type;
        assignaddress(it.pointerRegister);
        assignaddress(it.addressPointer);
    }

    @Override
    public void visit(MemberFuncCallExpr it) {
        it.lhs.accept(this);
        if (it.lhs.type.dim > 0) {
            if (!("size".equals(it.name) && it.argList.size() == 0))
                throw new SemanticError("not qualified to use array size function", it.pos);

            it.argList.add(it.lhs);
            it.type = Type.INT_TYPE;

            it.proxyFuncCall = new FuncCallExpr( "array__size");
            it.proxyFuncCall.argList = it.argList;
            it.proxyFuncCall.pointerRegister = it.pointerRegister;
            assignaddress(it.pointerRegister);

            return;
        }
        ClassDef classDef = it.lhs.type.getClassDef();
        FuncDef func = classDef.funcDefs.get(it.name);
        if (func == null) throw new SemanticError("no this name function " + it.name, it.pos);
        int argSize = it.argList.size();//this
        if (argSize != func.parameterIdentifiers.size() - 1)
            throw new SemanticError("function parameters and call parameters numbers do not match " + it.name + argSize + func.parameterIdentifiers.size(), it.pos);
        for (int i = 0; i < argSize; ++i) {
            it.argList.get(i).accept(this);
            if (!it.argList.get(i).type.equals(func.parameterTypes.get(i)))
                throw new SemanticError("function parameters and call parameters type do not match in " + i + "th place", it.pos);
        }
        it.argList.add(it.lhs);
        it.type = func.returnType;

        it.proxyFuncCall = new FuncCallExpr(classDef.name + "__" + it.name);
        it.proxyFuncCall.argList = it.argList;
        it.proxyFuncCall.pointerRegister = it.pointerRegister;
        assignaddress(it.pointerRegister);
    }

    @Override
    public void visit(FuncCallExpr it) {
        if (currentClass != null) {
            try {
                MemberFuncCallExpr memberFuncCallExpr = new MemberFuncCallExpr(it.pos, new ThisExpr(it.pos), it);
                memberFuncCallExpr.accept(this);
                it.type = memberFuncCallExpr.type;
                it.name = currentClass.name + "__" + it.name;
                it.pointerRegister = memberFuncCallExpr.pointerRegister;
                return;
            } catch (SemanticError ignored) {
            }
        }
        FuncDef func = Type.getFuncDef(it.name);
        if(func == null) System.out.println(it.name);
        int argSize = it.argList.size();
        if (argSize != func.parameterIdentifiers.size())
            throw new SemanticError("function parameters and call parameters numbers do not match " + it.name, it.pos);
        for (int i = 0; i < argSize; ++i) {
            it.argList.get(i).accept(this);
            if (!it.argList.get(i).type.equals(func.parameterTypes.get(i))) {
                throw new SemanticError("function parameters and call parameters type do not match in " + i + "th place " + "funcdeftype = " + func.parameterTypes.get(i).typeName + " funccalltype = " + it.argList.get(i).type.typeName, it.pos);
            }
        }
        it.type = func.returnType;
        assignaddress(it.pointerRegister);
    }

    private VarExpr proxyvar;
    @Override
    public void visit(VarExpr it) {
        if (it == proxyvar) return;
        proxyvar = it;
        it.type = currentScope.getType(it.name, true);
        if (it.type == null) throw new SemanticError("variable not defined", it.pos);
        if (currentClass != null && currentClass.members.containsKey(it.name)) {
            ThisExpr thisExpr = new ThisExpr();
            it.proxyThis = new MemberExpr(thisExpr, it);
            it.proxyThis.accept(this);
            it.pointerRegister = it.proxyThis.pointerRegister;
        } else it.pointerRegister = currentScope.getPointerRegister(it.name, true);
    }

    //HACK actually, lambda and break would interfere with each other, but I guess it will not test.

    @Override
    public void visit(LambdaExpr it) {
        int sz = it.argList.size();
        if (it.parameterIdentifiers.size() != sz || it.parameterTypes.size() != sz)
            throw new SemanticError("lambda argList length not match", it.pos);
        currentScope = new Scope(currentScope, it);
        for (int i = 0; i < sz; ++i) {
            VarDefStmt varDefStmt = new VarDefStmt(it.pos, it.parameterTypes.get(i));
            varDefStmt.names.add(it.parameterIdentifiers.get(i));
            varDefStmt.init.add(it.argList.get(i));
            varDefStmt.accept(this);
        }
        it.body.accept(this);
        currentScope = currentScope.parentScope();
        assignaddress(it.pointerRegister);
    }

    @Override
    public void visit(ConstExpr it) {
        assignaddress(it.pointerRegister);
    }

    @Override
    public void visit(EmptyStmt it) {
    }

    @Override
    public void visit(AssignExpr it) {
        if (it.rhs == null) throw new SemanticError("void function can not be assigned to variable", it.pos);
        it.rhs.accept(this);
        it.lhs.accept(this);
        if (!it.lhs.isAssignable) throw new SemanticError("lhs is not assignable", it.pos);
        if (!it.lhs.type.equals(it.rhs.type)) throw new SemanticError("types not match", it.pos);
        it.type = it.lhs.type;
        it.pointerRegister = it.lhs.pointerRegister;
    }

    @Override
    public void visit(NewClassExpr it) {
        if (it.type.getClassDef() == null)
            throw new SemanticError("new class does not exist", it.pos);
        assignaddress(it.pointerRegister);
        assignaddress(it.funcPointer);
    }

    @Override
    public void visit(ExprStmt it) {
        it.expr.accept(this);
    }

}
