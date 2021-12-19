package AST;

import Util.position;

abstract public class ASTNode {
    public position pos;

    public ASTNode(position pos) {
        this.pos = pos;
    }


    public ASTNode() {
    }


    abstract public void accept(ASTVisitor visitor);
}
