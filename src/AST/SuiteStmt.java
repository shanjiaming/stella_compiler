package AST;

import Util.position;

import java.util.ArrayList;

public class SuiteStmt extends Stmt {
	public ArrayList<Stmt> stmts = new ArrayList<>();
	
	public SuiteStmt(position pos) {super(pos);}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
