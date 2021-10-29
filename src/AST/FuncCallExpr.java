package AST;

//import LLVMIR.Operand.entity;
//import LLVMIR.function;
import Util.position;

import java.util.ArrayList;

public class FuncCallExpr extends Expr {
	public String name;
	public ArrayList<Expr> argList = new ArrayList<>();
//	public entity thisEntity = null;
//	public function func;

	public FuncCallExpr(position pos, String name) {
		super(pos);
		this.name = name;
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
