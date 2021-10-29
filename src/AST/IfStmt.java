package AST;

import Util.position;

public class IfStmt extends Stmt {
	public Expr cond;
	public Stmt trueNode, falseNode;

	public IfStmt(position pos, Expr cond, Stmt trueNode, Stmt falseNode) {
		super(pos);
		this.cond = cond;
		this.trueNode = trueNode;
		this.falseNode = falseNode;
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
