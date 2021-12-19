package AST;

import Util.position;
import Util.Type;

public class NewClassExpr extends Expr {

	public NewClassExpr(Type type) {
		super(true);
		super.type = type;
	}

	public NewClassExpr(position pos, Type type) {
		super(pos,true);
		super.type = type;
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
