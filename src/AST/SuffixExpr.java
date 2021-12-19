package AST;

import Util.position;

public class SuffixExpr extends Expr{
    public Expr lhs;
    public String op;

    public SuffixExpr(Expr lhs, String op) {
        super(false);
        this.lhs = lhs;
        this.op = op;
    }


    public SuffixExpr(position pos, Expr lhs, String op) {
        super(pos,false);
        this.lhs = lhs;
        this.op = op;
    }

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
