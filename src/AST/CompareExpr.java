package AST;

import Util.position;

public class CompareExpr extends Expr {

	public Expr lhs, rhs;
	public String op;

	public CompareExpr(position pos, Expr lhs, Expr rhs) {
		super(pos);
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
