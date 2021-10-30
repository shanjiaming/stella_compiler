package Frontend;

import AST.*;
import Util.*;
import Util.MxError.SemanticError;

public class SemanticChecker extends ASTVisitor {
    private Scope currentScope;
    //	private classType currentClass = null;
    private String currentClassName = null;
    private Type currentReturnType = null;


    @Override
    public void visit(Program it) {

    }

    @Override
    public void visit(FuncDef it) {

    }

    @Override
    public void visit(ClassDef it) {

    }

    @Override
    public void visit(VarDefStmt it) {

    }

    @Override
    public void visit(SuiteStmt it) {

    }

    @Override
    public void visit(ConstructDef it) {

    }

    @Override
    public void visit(BinaryExpr it) {
        boolean intintReturnint = "*".equals(it.op) || "/".equals(it.op) || "%".equals(it.op) || "+".equals(it.op) || "-".equals(it.op) || "<<".equals(it.op) || ">>".equals(it.op) || "&".equals(it.op) || "^".equals(it.op) || "|".equals(it.op);
        boolean compareReturnbool = "<".equals(it.op) || ">".equals(it.op) || "<=".equals(it.op) || ">=".equals(it.op) || "==".equals(it.op) || "!=".equals(it.op);
        boolean boolboolReturnbool = "==".equals(it.op) || "!=".equals(it.op) || "&&".equals(it.op) || "||".equals(it.op);

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
        if(!(
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
        if (!it.isAssignable) throw new SemanticError("prefix not assignable", it.pos);
        if(!it.lhs.type.equals(Type.INT_TYPE)) throw new SemanticError("types not match", it.pos);
        it.type = it.lhs.type;
    }

    @Override
    public void visit(SuffixExpr it) {
        if (!it.isAssignable) throw new SemanticError("suffix not assignable", it.pos);
        if(!it.lhs.type.equals(Type.INT_TYPE)) throw new SemanticError("types not match", it.pos);
        it.type = it.lhs.type;
    }

    @Override
    public void visit(IfStmt it) {

    }

    @Override
    public void visit(ThisExpr it) {

    }

    @Override
    public void visit(NewArrayExpr it) {

    }

    @Override
    public void visit(ForStmt it) {

    }

    @Override
    public void visit(ContinueStmt it) {

    }

    @Override
    public void visit(BreakStmt it) {

    }

    @Override
    public void visit(ReturnStmt it) {

    }

    @Override
    public void visit(IndexExpr it) {
        if(it.lhs.type.dim <= 0) throw new SemanticError("cannot be indexed", it.pos);
        it.type = Type.reduceDim(it.lhs.type);
    }

    @Override
    public void visit(MemberExpr it) {
        it.type = it.rhs.type;
    }

    @Override
    public void visit(FuncCallExpr it) {

        it.type = Type.stringToType(it.name);//function parameters should also be added.
    }

    @Override
    public void visit(VarExpr it) {
        it.type = Type.stringToType(it.name);
    }

    @Override
    public void visit(LambdaExpr it) {

    }

    @Override
    public void visit(ConstExpr it) {

    }

    @Override
    public void visit(EmptyStmt it) {}

    @Override
    public void visit(AssignExpr it) {
        if (!it.lhs.isAssignable) throw new SemanticError("lhs is not assignable", it.pos);
        if (!it.lhs.type.equals(it.rhs.type)) throw new SemanticError("types not match", it.pos);
        it.type = it.lhs.type;
    }

    @Override
    public void visit(NewClassExpr it) {}

    @Override
    public void visit(MemberFuncCallExpr memberFuncCallExpr) {

    }
}
