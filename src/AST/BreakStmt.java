package AST;

import Util.position;

public class BreakStmt extends Stmt {
	public Stmt loopNode;

	public BreakStmt() {}
	public BreakStmt(position pos) {super(pos);}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
