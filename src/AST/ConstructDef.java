package AST;

import Util.position;

public class ConstructDef extends ASTNode{
    public FuncDef proxyFunc;

    public SuiteStmt body;

    public ConstructDef(SuiteStmt body) {
        this.body = body;
    }
    public ConstructDef(position pos, SuiteStmt body) {
        super(pos);
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
