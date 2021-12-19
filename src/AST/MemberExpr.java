package AST;

import Util.position;

public class MemberExpr extends Expr{
    public Expr lhs;
    public VarExpr rhs;

    public MemberExpr(Expr lhs, VarExpr rhs) {
        super(true);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public MemberExpr(position pos, Expr lhs, VarExpr rhs) {
        super(pos,true);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
