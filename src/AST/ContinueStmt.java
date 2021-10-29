package AST;

import Util.position;

public class ContinueStmt extends Stmt {
	public Stmt loopNode;

	public ContinueStmt(position pos) {super(pos);}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
