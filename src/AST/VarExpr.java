package AST;

import Util.position;

import java.util.ArrayList;

public class VarExpr extends Expr {
	public String name;

	public VarExpr(position pos, String name) {
		super(pos, true);
		this.name = name;
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
