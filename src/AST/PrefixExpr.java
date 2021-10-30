package AST;

import Util.position;

public class PrefixExpr extends Expr{
    public Expr lhs;
    public String op;

    public PrefixExpr(position pos, Expr lhs, String op) {
        super(pos,true);
        this.lhs = lhs;
        this.op = op;
    }

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
