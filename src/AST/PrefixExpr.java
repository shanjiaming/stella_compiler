package AST;

import Util.position;

public class PrefixExpr extends Expr{
    Expr lhs;
    String op;

    public PrefixExpr(position pos, Expr lhs, String op) {
        super(pos);
        this.lhs = lhs;
        this.op = op;
    }

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
