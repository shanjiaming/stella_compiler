package AST;

import Util.position;
import Util.Type;

abstract public class Expr extends ASTNode{

    public position pos;
    public boolean isAssignable;
    public Type type;

    public Expr(position pos, boolean isAssignable) {
        super(pos);
        this.isAssignable = isAssignable;
    }
}
