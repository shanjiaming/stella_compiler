package AST;

import IR.PointerRegister;
import Util.position;
import Util.Type;


abstract public class Expr extends ASTNode{

    public position pos;
    public boolean isAssignable;
    public Type type;
    public PointerRegister pointerRegister = new PointerRegister();

    public Expr(boolean isAssignable) {
        this.isAssignable = isAssignable;
    }
    public Expr(position pos, boolean isAssignable) {
        super(pos);
        this.isAssignable = isAssignable;
    }
}
