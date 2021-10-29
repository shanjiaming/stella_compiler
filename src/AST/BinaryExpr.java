package AST;

import Util.position;

public class BinaryExpr extends Expr{
    Expr lhs, rhs;
    String op;

    public BinaryExpr(position pos, Expr lhs, Expr rhs, String op) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
    }

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
