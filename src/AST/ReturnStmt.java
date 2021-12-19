package AST;

import Util.position;

public class ReturnStmt extends Stmt {
	public Expr returnExpr;

	public ReturnStmt() {}
	public ReturnStmt(position pos) {super(pos);}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
