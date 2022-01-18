package AST;

import IR.PointerRegister;
import Util.position;

import java.util.ArrayList;

public class NewArrayExpr extends Expr {
	public ArrayList<Expr> dims = new ArrayList<>();

	public NewArrayExpr() {
		super(true);
	}
	public NewArrayExpr(position pos) {
		super(pos,true);
	}

	public PointerRegister tempPointer = new PointerRegister();

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
