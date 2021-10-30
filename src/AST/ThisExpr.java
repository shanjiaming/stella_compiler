package AST;

import Util.position;

public class ThisExpr extends Expr {
	public ThisExpr(position pos) {super(pos,true);}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
