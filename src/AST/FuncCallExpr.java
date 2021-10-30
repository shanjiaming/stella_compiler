package AST;

import Util.position;

import java.util.ArrayList;

public class FuncCallExpr extends Expr {
	public String name;
	public ArrayList<Expr> argList = new ArrayList<>();

	public FuncCallExpr(position pos, String name) {
		super(pos,false);
		this.name = name;
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
