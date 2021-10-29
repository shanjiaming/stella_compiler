package AST;

import Util.position;

public class ConstructDef extends ASTNode{
    public SuiteStmt body;

    public ConstructDef(position pos, SuiteStmt body) {
        super(pos);
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
