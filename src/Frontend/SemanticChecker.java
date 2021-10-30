package Frontend;

import AST.*;
import Util.*;
import Util.MxError.SemanticError;

public class SemanticChecker extends ASTVisitor {
    private Scope currentScope;
    private ClassDef currentClass = null;
    private FuncDef currentFunc = null;
    private LambdaExpr currentLambda = null;


    //todo inline function
    //todo main function
    //todo know that not void function should has return statement except main

    @Override
    public void visit(Program it) {
        for (ProgramUnit programUnit : it.programUnits) {
            ((ASTNode) programUnit).accept(this);
        }
    }

    @Override
    public void visit(FuncDef it) {
        int sz = it.parameterTypes.size();
        if (sz != it.parameterIdentifiers.size())
            throw new SemanticError("function parameters and identifiers numbers do not match", it.pos);
        currentScope = new Scope(currentScope);
        for (int i = 0; i < sz; ++i) {
            currentScope.defineVariable(it.parameterIdentifiers.get(i), it.parameterTypes.get(i), it.pos);
        }
        currentFunc = it;
        ((ASTNode) it.body).accept(this);
        currentFunc = null;
        currentScope = currentScope.parentScope();
    }

    @Override
    public void visit(ClassDef it) {
        currentScope = new Scope(currentScope);
        currentClass = it;
        it.members.forEach((str, type) -> currentScope.defineVariable(str, type, it.pos));
        it.constructDef.accept(this);
        it.funcDefs.values().forEach(funcDef -> funcDef.accept(this));
        currentClass = null;
        currentScope = currentScope.parentScope();
    }

    @Override
    public void visit(SuiteStmt it) {

    }

//TODO handle null value

    @Override
    public void visit(VarDefStmt it) {
        it.names.forEach(name -> currentScope.defineVariable(name, it.varType, it.pos));
        for (Expr expr : it.init) {
            expr.accept(this);
            if (!it.varType.equals(expr.type)) throw new SemanticError("initialize type not match", it.pos);
        }
    }

    @Override
    public void visit(ConstructDef it) {
        currentScope = new Scope(currentScope);
        it.body.accept(this);
        currentScope = currentScope.parentScope();
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
    }

    @Override
    public void visit(PrefixExpr it) {
        it.lhs.accept(this);
        if (!it.isAssignable) throw new SemanticError("prefix not assignable", it.pos);
        if (!it.lhs.type.equals(Type.INT_TYPE)) throw new SemanticError("types not match", it.pos);
        it.type = it.lhs.type;
    }

    @Override
    public void visit(SuffixExpr it) {
        it.lhs.accept(this);
        if (!it.isAssignable) throw new SemanticError("suffix not assignable", it.pos);
        if (!it.lhs.type.equals(Type.INT_TYPE)) throw new SemanticError("types not match", it.pos);
        it.type = it.lhs.type;
    }

    @Override
    public void visit(IfStmt it) {
        it.cond.accept(this);
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
    }

    @Override
    public void visit(NewArrayExpr it) {

    }

    @Override
    public void visit(ForStmt it) {
        currentScope = new Scope(currentScope, it);
        it.init.accept(this);
        it.cond.accept(this);
        it.incr.accept(this);
        it.body.accept(this);
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
        if (currentFunc == null && currentLambda == null) {
            throw new SemanticError("return statement out of function", it.pos);
        }
        if (it.returnExpr != null) it.returnExpr.accept(this);
        if (currentLambda != null) currentLambda.type = (it.returnExpr == null) ? null : it.returnExpr.type;
        if (currentFunc != null)
            if (it.returnExpr == null && currentFunc.returnType != null ||
                    it.returnExpr != null && currentFunc.returnType == null ||
                    currentFunc.returnType != null && !currentFunc.returnType.equals(it.returnExpr.type))
                throw new SemanticError("return type does not match with function declaration", it.pos);
    }

    @Override
    public void visit(IndexExpr it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type.dim <= 0) throw new SemanticError(it.lhs.type + "cannot be indexed", it.pos);
        if (!Type.INT_TYPE.equals(it.rhs.type))
            throw new SemanticError("should use int to index, but use " + it.type, it.pos);
        it.type = Type.reduceDim(it.lhs.type);
    }

    @Override
    public void visit(MemberExpr it) {
        it.lhs.accept(this);
        ClassDef classDef = Type.getClassDef(it.lhs.type);
        Type type = classDef.members.get(it.rhs.name);
        if (type == null) throw new SemanticError("no this name member variable", it.pos);
        it.type = type;
    }

    @Override
    public void visit(MemberFuncCallExpr it) {
        it.lhs.accept(this);
        ClassDef classDef = Type.getClassDef(it.lhs.type);


        FuncDef func = Type.getFuncDef(Type.stringToType(it.name));
        if (func == null) throw new SemanticError("no this name function", it.pos);
        if (func.returnType == null) throw new SemanticError("void return cannot be assigned to variable", it.pos);
        int argSize = it.argList.size();
        if (argSize != func.parameterIdentifiers.size())
            throw new SemanticError("function parameters and call parameters numbers do not match", it.pos);
        for (int i = 0; i < argSize; ++i) {
            if (!it.argList.get(i).type.equals(func.parameterTypes.get(i)))
                throw new SemanticError("function parameters and call parameters type do not match in " + i + "th place", it.pos);
        }
        it.type = func.returnType;
    }

    @Override
    public void visit(FuncCallExpr it) {
        FuncDef func = Type.getFuncDef(Type.stringToType(it.name));
        if (func == null) throw new SemanticError("no this name function", it.pos);
        if (func.returnType == null) throw new SemanticError("void return cannot be assigned to variable", it.pos);
        int argSize = it.argList.size();
        if (argSize != func.parameterIdentifiers.size())
            throw new SemanticError("function parameters and call parameters numbers do not match", it.pos);
        for (int i = 0; i < argSize; ++i) {
            if (!it.argList.get(i).type.equals(func.parameterTypes.get(i)))
                throw new SemanticError("function parameters and call parameters type do not match in " + i + "th place", it.pos);
        }
        it.type = func.returnType;
    }

    @Override
    public void visit(VarExpr it) {
        it.type = currentScope.getType(it.name, true);
        if (it.type == null) throw new SemanticError("variable not defined", it.pos);
    }

    @Override
    public void visit(LambdaExpr it) {
        int sz = it.argList.size();
        if (it.parameterIdentifiers.size() != sz || it.parameterTypes.size() != sz)
            throw new SemanticError("lambda argList length not match", it.pos);
        currentLambda = it;
        currentScope = new Scope(currentScope);
        for (int i = 0; i < sz; ++i){
            VarDefStmt varDefStmt = new VarDefStmt(it.pos, it.parameterTypes.get(i));
            varDefStmt.names.add(it.parameterIdentifiers.get(i));
            varDefStmt.init.add(it.argList.get(i));
            varDefStmt.accept(this);
        }
        it.body.accept(this);
        currentScope = null;
        currentLambda = null;
    }

    @Override
    public void visit(ConstExpr it) {

    }

    @Override
    public void visit(EmptyStmt it) {
    }

    @Override
    public void visit(AssignExpr it) {
        if (!it.lhs.isAssignable) throw new SemanticError("lhs is not assignable", it.pos);
        if (!it.lhs.type.equals(it.rhs.type)) throw new SemanticError("types not match", it.pos);
        it.type = it.lhs.type;
    }

    @Override
    public void visit(NewClassExpr it) {
        if (Type.getClassDef(it.type) == null) throw new SemanticError("new class does not exist", it.pos);
    }


}
