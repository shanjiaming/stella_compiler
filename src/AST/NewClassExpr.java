package AST;

import Util.position;

public class NewClassExpr extends Expr {
	public Type type;

	public NewClassExpr(position pos, Type type) {
		super(pos);
		this.type = type;
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
