package AST;

import Util.position;

public class ConstExpr extends Expr {
	public String value, type;

	public ConstExpr(position pos, String value, String type) {
		super(pos);
		this.value = value;
		this.type = type;
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
