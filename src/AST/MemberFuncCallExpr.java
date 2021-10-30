package AST;

import Util.position;

public class MemberFuncCallExpr extends Expr{
    public Expr lhs, rhs;

    public MemberFuncCallExpr(position pos, Expr lhs, Expr rhs) {
        super(pos,false);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
