package Frontend;

import AST.*;
import Util.*;
import Util.MxError.SemanticError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class SemanticChecker implements ASTVisitor {
    private Scope currentScope;
    //	private classType currentClass = null;
    private String currentClassName = null;
    private Type currentReturnType = null;
    private final globalScope gScope;

    public SemanticChecker(globalScope gScope) {
        this.currentScope = this.gScope = gScope;
    }


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
        if (Type.INTTYPE.equals(ltype)) {
            if (intintReturnint) it.type = Type.INTTYPE;
            else if (compareReturnbool) it.type = Type.BOOLTYPE;
            else throw new SemanticError("types not match", it.pos);
        } else if (Type.BOOLTYPE.equals(ltype)) {
            if (boolboolReturnbool) it.type = Type.BOOLTYPE;
            else throw new SemanticError("types not match", it.pos);
        } else if (Type.STRINGTYPE.equals(ltype)) {
            if ("+".equals(it.op)) it.type = Type.STRINGTYPE;
            else if (compareReturnbool) it.type = Type.BOOLTYPE;
            else throw new SemanticError("types not match", it.pos);
        } else if ("==".equals(it.op) || "!=".equals(it.op)) {
            it.type = Type.BOOLTYPE;
        } else throw new SemanticError("types not match", it.pos);

    }

    @Override
    public void visit(UnaryExpr it) {
        if(!(
                (it.lhs.type.equals(Type.INTTYPE) &&
                        (it.op.equals("+") || it.op.equals("-") || it.op.equals("~"))
                )
                        ||
                        (it.lhs.type.equals(Type.BOOLTYPE) && it.op.equals("!"))
        ))
        throw new SemanticError("types not match", it.pos);
        it.type = it.lhs.type;
    }

    @Override
    public void visit(PrefixExpr it) {
        if (!it.isAssignable) throw new SemanticError("prefix not assignable", it.pos);
        if(!it.lhs.type.equals(Type.INTTYPE)) throw new SemanticError("types not match", it.pos);
        it.type = it.lhs.type;
    }

    @Override
    public void visit(SuffixExpr it) {
        if (!it.isAssignable) throw new SemanticError("suffix not assignable", it.pos);
        if(!it.lhs.type.equals(Type.INTTYPE)) throw new SemanticError("types not match", it.pos);
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
    }

    @Override
    public void visit(MemberExpr it) {
        it.type = it.rhs.type;
    }

    @Override
    public void visit(FuncCallExpr it) {

        it.type = Type.StringToType(it.name);//function parameters should also be added.
    }

    @Override
    public void visit(VarExpr it) {
        it.type = Type.StringToType(it.name);
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
