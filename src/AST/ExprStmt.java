package AST;

import Util.position;

import java.util.ArrayList;

public class ExprStmt extends Stmt {
    public Expr expr;

    public ExprStmt(position pos, Expr expr) {
        super(pos);
        this.expr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
