package AST;

import Util.position;

import java.util.AbstractMap;
import java.util.ArrayList;

public class LambdaExpr extends Expr {
	public ArrayList<Type> parameterTypes = new ArrayList<>();
	public ArrayList<String> parameterIdentifiers = new ArrayList<>();
	public SuiteStmt body;
	public ArrayList<Expr> argList = new ArrayList<>();

	public LambdaExpr(position pos, SuiteStmt body) {
		super(pos);
		this.body = body;
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
