package AST;

import Util.position;

import java.util.ArrayList;

public class NewArrayExpr extends Expr {
	public Type type;
	public ArrayList<Expr> dims = new ArrayList<>();
	public int totalDim;

	public NewArrayExpr(position pos, Type type) {
		super(pos);
		this.type = type;
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
