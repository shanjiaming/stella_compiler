package Frontend;

import AST.*;
import Parser.MxBaseVisitor;
import Parser.MxParser;
import Util.Type;
import Util.globalScope;
import Util.position;
import org.antlr.v4.runtime.ParserRuleContext;
import AST.binaryExprNode.binaryOpType;
import AST.cmpExprNode.cmpOpType;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {

    private globalScope gScope;
    public ASTBuilder(globalScope gScope) {
        this.gScope = gScope;
    }

    Type intType, boolType;

    @Override public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        RootNode root = new RootNode(new position(ctx), (FnRootNode)visit(ctx.mainFn()));
        ctx.classDef().forEach(cd -> root.strDefs.add((structDefNode) visit(cd)));
        return root;
    }

    @Override public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
        structDefNode structDef = new structDefNode(new position(ctx), ctx.Identifier().toString());
        ctx.varDef().forEach(vd -> structDef.varDefs.add((varDefStmtNode) visit(vd)));
        return structDef;
    }

    @Override public ASTNode visitMainFn(MxParser.MainFnContext ctx) {
        FnRootNode root = new FnRootNode(new position(ctx));
        intType = root.intType;
        boolType = root.boolType;
        gScope.addType("int", intType, root.pos);
        gScope.addType("bool", boolType, root.pos);

        if (ctx.suite() != null) {
            for (ParserRuleContext stmt : ctx.suite().statement())
                root.stmts.add((StmtNode) visit(stmt));
        }
        return root;
    }

    @Override public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
        ExprNode expr = null;
        String typeName;
        if (ctx.type().Int() != null) typeName = ctx.type().Int().toString();
        else typeName = ctx.type().Identifier().toString();
        if (ctx.expression() != null) expr = (ExprNode)visit(ctx.expression());

        return new varDefStmtNode(typeName,
                ctx.Identifier().toString(),
                expr, new position(ctx));
    }

    @Override public ASTNode visitSuite(MxParser.SuiteContext ctx) {
        blockStmtNode node = new blockStmtNode(new position(ctx));

        if (!ctx.statement().isEmpty()) {
            for (ParserRuleContext stmt : ctx.statement()) {
                StmtNode tmp = (StmtNode)visit(stmt);
                if (tmp != null) node.stmts.add(tmp);
            }
        }
        return node;
    }

    @Override public ASTNode visitBlock(MxParser.BlockContext ctx) {
        return visit(ctx.suite());
    }

    @Override public ASTNode visitVardefStmt(MxParser.VardefStmtContext ctx) { return visit(ctx.varDef()); }

    @Override public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
        StmtNode thenStmt = (StmtNode)visit(ctx.trueStmt), elseStmt = null;
        ExprNode condition = (ExprNode)visit(ctx.expression());
        if (ctx.falseStmt != null) elseStmt = (StmtNode)visit(ctx.falseStmt);
        return new ifStmtNode(condition, thenStmt, elseStmt, new position(ctx));
    }

    @Override public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        ExprNode value = null;
        if (ctx.expression() != null) value = (ExprNode) visit(ctx.expression());
        return new returnStmtNode(value, new position(ctx));
    }

    @Override public ASTNode visitPureExprStmt(MxParser.PureExprStmtContext ctx) {
        return new exprStmtNode((ExprNode) visit(ctx.expression()), new position(ctx));
    }

    @Override public ASTNode visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
        return null;
    }

    @Override public ASTNode visitAtomExpr(MxParser.AtomExprContext ctx) {
        return visit(ctx.primary());
    }

    @Override public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
        ExprNode lhs = (ExprNode) visit(ctx.expression(0)),
                rhs = (ExprNode) visit(ctx.expression(1));
        binaryOpType biOp = ctx.Plus() != null ? binaryOpType.add :
                (ctx.Minus() != null ? binaryOpType.sub : null);
        cmpOpType cmpOp = ctx.Equal() != null ? cmpOpType.eq :
                (ctx.NotEqual() != null ? cmpOpType.neq : null);

        return biOp != null ? (new binaryExprNode(lhs, rhs, biOp, intType, new position(ctx))) :
                (new cmpExprNode(lhs, rhs, cmpOp, boolType, new position(ctx)));
    }

    @Override public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
        ExprNode lhs = (ExprNode) visit(ctx.expression(0)),
                rhs = (ExprNode) visit(ctx.expression(1));
        return new assignExprNode(lhs, rhs, new position(ctx));
    }

    @Override public ASTNode visitPrimary(MxParser.PrimaryContext ctx) {
        if (ctx.expression() != null) return visit(ctx.expression());
        else if (ctx.literal() != null) return visit(ctx.literal());
        else return new varExprNode(ctx.Identifier().toString(), new position(ctx.Identifier()));
    }

    @Override public ASTNode visitLiteral(MxParser.LiteralContext ctx) {
        return new constExprNode(Integer.parseInt(ctx.DecimalInteger().toString()),
                intType, new position(ctx));
    }

}
