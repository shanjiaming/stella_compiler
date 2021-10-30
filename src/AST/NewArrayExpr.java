package AST;

import Util.position;

import java.util.ArrayList;

public class NewArrayExpr extends Expr {
	public ArrayList<Expr> dims = new ArrayList<>();

	public NewArrayExpr(position pos) {
		super(pos,true);
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
