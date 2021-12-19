package AST;

import Util.position;
import Util.Type;
import IR.Entity;


abstract public class Expr extends ASTNode{

    public position pos;
    public boolean isAssignable;
    public Type type;
    public Entity entity;

    public Expr(boolean isAssignable) {
        this.isAssignable = isAssignable;
    }
    public Expr(position pos, boolean isAssignable) {
        super(pos);
        this.isAssignable = isAssignable;
    }
}
