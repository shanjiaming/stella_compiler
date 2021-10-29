package Frontend;

import AST.*;
import Parser.MxBaseVisitor;
import Util.MxError.ASTBuilderError;
import Util.globalScope;
import Util.position;
import org.antlr.v4.runtime.tree.TerminalNode;
import Parser.MxParser.*;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {

    public globalScope gScope;

    public ASTBuilder(globalScope gScope) {
        this.gScope = gScope;
    }

    @Override
    public ASTNode visitProgram(ProgramContext ctx) {
        Program program = new Program(new position(ctx));
        ctx.classDef().forEach(classDef -> program.classDefs.add((ClassDef) visit(classDef)));
        ctx.funcDef().forEach(funcDef -> program.funcDefs.add((FuncDef) visit(funcDef)));
        ctx.varDef().forEach(varDef -> program.varDefStmts.add((VarDefStmt) visit(varDef)));
        return program;
    }


    @Override
    public ASTNode visitClassDef(ClassDefContext ctx) {
        ClassDef classDef = new ClassDef(new position(ctx), ctx.Identifier().getText());
        ctx.constructDef().forEach(constructDef -> {
            if (!classDef.name.equals(constructDef.Identifier().getText())) {
                throw new ASTBuilderError("Constructor name not equal with class name", new position(ctx));
            }
            classDef.constructDefs.add((ConstructDef) visit(constructDef));
        });
        ctx.funcDef().forEach(funcDef -> classDef.funcDefs.add((FuncDef) visit(funcDef)));
        ctx.varDef().forEach(varDef -> classDef.varDefStmts.add((VarDefStmt) visit(varDef)));
        return classDef;
    }


    @Override
    public ASTNode visitFuncDef(FuncDefContext ctx) {
        FuncDef funcDef = new FuncDef(
                new position(ctx),
                ctx.Identifier().getText(),
                (SuiteStmt) visit(ctx.suite()),
                (ctx.Void() == null) ? null : (Type) visit(ctx.type())
        );
        ctx.parameterList().type().forEach(type -> funcDef.parameterTypes.add((Type) visit(type)));
        ctx.parameterList().Identifier().forEach(identifier -> funcDef.parameterIdentifiers.add(identifier.getText()));
        return funcDef;
    }


    @Override
    public ASTNode visitIfStmt(IfStmtContext ctx) {
        return new IfStmt(new position(ctx), (Expr) visit(ctx.expr()), (Stmt) visit(ctx.stmt(0)), (Stmt) visit(ctx.stmt(1)));//HACK shoubld use statement without {} here
    }


    @Override
    public ASTNode visitForStmt(ForStmtContext ctx) {
        return new ForStmt(new position(ctx), (Expr) visit(ctx.forInit), (Expr) visit(ctx.forCondition), (Expr) visit(ctx.forIncrease), (Stmt) visit(ctx.stmt()));//HACK shoubld use statement without {} here
    }


    @Override
    public ASTNode visitWhileStmt(WhileStmtContext ctx) {
        return new ForStmt(new position(ctx), null, (Expr) visit(ctx.expr()), null, (Stmt) visit(ctx.stmt()));//HACK shoubld use statement without {} here
        //HACK only use one statement, did not define expr','expr
    }

    @Override
    public ASTNode visitType(TypeContext ctx) {
        return new Type(new position(ctx), ctx.baseType().getText(), ctx.LeftBracket().size());
    }

    @Override
    public ASTNode visitConstValue(ConstValueContext ctx) {
        return new ConstExpr(
                new position(ctx),
                ctx.getText(),
                ctx.IntConst() != null ? "int" :
                        ctx.BoolConst() != null ? "bool" :
                                ctx.StringConst() != null ? "string" :
                                        ctx.Null() != null ? "null" :
                                                null);
    }

    @Override
    public ASTNode visitVarDef(VarDefContext ctx) {
        VarDefStmt varDefStmt = new VarDefStmt(new position(ctx), (Type) visit(ctx.type()));
        ctx.varSingleDef().forEach(varDef -> {
            varDefStmt.names.add(varDef.Identifier().getText());
            varDefStmt.init.add(varDef.expr() == null ? null : (Expr) visit(varDef.expr()));
        });
        return varDefStmt;
    }

    @Override
    public ASTNode visitConstructDef(ConstructDefContext ctx) {
        return new ConstructDef(new position(ctx), (SuiteStmt) visit(ctx.suite()));
    }

    @Override
    public ASTNode visitValue(ValueContext ctx) {
        ConstValueContext constValueContext = ctx.constValue();
        if (constValueContext != null) return visit(constValueContext);
        NewItemContext newItemContext = ctx.newItem();
        if (newItemContext != null) return visit(newItemContext);
        LambdaContext lambdaContext = ctx.lambda();
        if (lambdaContext != null) return visit(lambdaContext);
        TerminalNode thisContext = ctx.This();
        if (thisContext != null) return new ThisExpr(new position(ctx));
        ExprListContext exprListContext = ctx.exprList();
        if (exprListContext != null) {
            FuncCallExpr funcCallExpr = new FuncCallExpr(new position(ctx), ctx.Identifier().getText());
            ctx.exprList().expr().forEach(expr -> funcCallExpr.argList.add((Expr) visit(expr)));
            return funcCallExpr;
        }
        return new VarExpr(new position(ctx), ctx.Identifier().getText());
    }

    @Override
    public ASTNode visitNewInvalidArray(NewInvalidArrayContext ctx) {
        throw new ASTBuilderError("invalid new expression.", new position(ctx));
    }

    @Override
    public ASTNode visitNewArray(NewArrayContext ctx) {
        NewArrayExpr newArrayExpr = new NewArrayExpr(new position(ctx), (Type) visit(ctx.baseType()));
        ctx.expr().forEach(exp -> newArrayExpr.dims.add((Expr) visit(exp)));
        newArrayExpr.totalDim = ctx.LeftBracket().size();
        return newArrayExpr;
    }

    @Override
    public ASTNode visitNewClass(NewClassContext ctx) {
        return new NewClassExpr(new position(ctx), (Type) visit(ctx.baseType()));
    }


    @Override
    public ASTNode visitLambda(LambdaContext ctx) {
        LambdaExpr lambdaExpr = new LambdaExpr(new position(ctx), (SuiteStmt) visit(ctx.suite()));
        ctx.exprList().expr().forEach(expr -> lambdaExpr.argList.add((Expr) visit(expr)));
        ctx.parameterList().type().forEach(type -> lambdaExpr.parameterTypes.add((Type) visit(type)));
        ctx.parameterList().Identifier().forEach(identifier -> lambdaExpr.parameterIdentifiers.add(identifier.getText()));
        return lambdaExpr;
    }

    @Override
    public ASTNode visitIndexExpr(IndexExprContext ctx) {
        return new IndexExpr(new position(ctx), (Expr) visit(ctx.expr(0)), (Expr) visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitPrefixExpr(PrefixExprContext ctx) {
        return new PrefixExpr(new position(ctx), (Expr) visit(ctx.expr()), ctx.op.getText());
    }

    @Override
    public ASTNode visitValueExpr(ValueExprContext ctx) {
        return visit(ctx.value());
    }

    @Override
    public ASTNode visitUnaryExpr(UnaryExprContext ctx) {
        return new UnaryExpr(new position(ctx), (Expr) visit(ctx.expr()), ctx.op.getText());
    }

    @Override
    public ASTNode visitSuffixExpr(SuffixExprContext ctx) {
        return new SuffixExpr(new position(ctx), (Expr) visit(ctx.expr()), ctx.op.getText());
    }

    @Override
    public ASTNode visitBinaryExpr(BinaryExprContext ctx) {
        return new BinaryExpr(new position(ctx), (Expr) visit(ctx.expr(0)), (Expr) visit(ctx.expr(1)), ctx.op.getText());
    }

    @Override
    public ASTNode visitMemberExpr(MemberExprContext ctx) {
        ExprListContext arglists = ctx.exprList();
        Expr expr;
        if (arglists == null) {
            expr = new VarExpr(new position(ctx),ctx.Identifier().getText());
        }else {
            expr = new FuncCallExpr(new position(ctx),ctx.Identifier().getText());
            arglists.expr().forEach(arg->((FuncCallExpr)expr).argList.add((Expr) visit(arg)));
        }
        return new MemberExpr(new position(ctx), (Expr)visit(ctx.expr()),expr);
    }

    @Override
    public ASTNode visitParenExpr(ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public ASTNode visitSuite(SuiteContext ctx) {
        SuiteStmt suiteStmt = new SuiteStmt(new position(ctx));
        ctx.stmt().forEach(stmt -> suiteStmt.stmts.add((Stmt) visit(stmt)));
        return suiteStmt;
    }



    @Override
    public ASTNode visitVarDefStmt(VarDefStmtContext ctx) {
        return visit(ctx.varDef());
    }

    @Override
    public ASTNode visitAssignExpr(AssignExprContext ctx) {
        return new AssignExpr(new position(ctx), (Expr)visit(ctx.expr(0)), (Expr)visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitReturnStmt(ReturnStmtContext ctx) {
        ReturnStmt returnStmt = new ReturnStmt(new position(ctx));
        if (ctx.expr() != null) returnStmt.returnExpr = (Expr) visit(ctx.expr());
        return returnStmt;
    }

    @Override
    public ASTNode visitBreakStmt(BreakStmtContext ctx) {
        return new BreakStmt(new position(ctx));
    }

    @Override
    public ASTNode visitContinueStmt(ContinueStmtContext ctx) {
        return new ContinueStmt(new position(ctx));
    }

    @Override
    public ASTNode visitBlockStmt(BlockStmtContext ctx) {
        return visit(ctx.suite());
    }

}
