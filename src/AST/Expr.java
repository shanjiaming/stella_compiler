package AST;

import Util.position;

abstract public class Expr extends ASTNode{

    public position pos;

    public Expr(position pos) {
        super(pos);
    }
}
