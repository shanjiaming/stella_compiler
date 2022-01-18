package AST;

import IR.PointerRegister;
import Util.position;

abstract public class AddressNode extends Expr{
    public PointerRegister addressPointer = new PointerRegister();

    public AddressNode(boolean isAssignable) {
        super(isAssignable);
    }

    public AddressNode(position pos, boolean isAssignable) {
        super(pos, isAssignable);
    }

}
