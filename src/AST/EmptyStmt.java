package AST;

import Util.position;

public class EmptyStmt extends Stmt {
	public Stmt loopNode;

	public EmptyStmt(position pos) {super(pos);}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
