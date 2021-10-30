package AST;

import Util.position;

public class AssignExpr extends Expr{
    public Expr lhs, rhs;

    public AssignExpr(position pos, Expr lhs, Expr rhs) {
        super(pos, true);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
