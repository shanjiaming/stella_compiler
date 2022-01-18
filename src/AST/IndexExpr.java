package AST;

import IR.PointerRegister;
import Util.position;

public class IndexExpr extends AddressNode{
    public Expr lhs, rhs;

    public IndexExpr(Expr lhs, Expr rhs) {
        super(true);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IndexExpr(position pos, Expr lhs, Expr rhs) {
        super(pos, true);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public PointerRegister tempPointer = new PointerRegister();

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
