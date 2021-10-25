package AST;

import Util.position;

public class varExprNode extends ExprNode {
    public String name;

    public varExprNode(String name, position pos) {
        super(pos);
        this.name = name;
        type = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable() {return true;}
}
