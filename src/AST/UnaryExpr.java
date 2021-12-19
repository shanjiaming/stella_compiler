package AST;

import Util.position;

public class UnaryExpr extends Expr{
    public Expr lhs;
    public String op;

    public UnaryExpr(Expr lhs, String op) {
        super(false);
        this.lhs = lhs;
        this.op = op;
    }

    public UnaryExpr(position pos, Expr lhs, String op) {
        super(pos,false);
        this.lhs = lhs;
        this.op = op;
    }

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
