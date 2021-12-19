package AST;

import Util.Type;
import Util.position;

public class ConstExpr extends Expr {
	public String value;

	public ConstExpr(String value, String type) {
		super(false);
		this.value = value;
		super.type = Type.stringToType(type);
	}

	public ConstExpr(position pos, String value, String type) {
		super(pos,false);
		this.value = value;
		super.type = Type.stringToType(type);
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
