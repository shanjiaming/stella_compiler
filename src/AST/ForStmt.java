package AST;

import Util.position;

public class ForStmt extends Stmt {
	public Expr init = null, cond = null, incr = null;
	public Stmt body;


	public ForStmt(position pos, Expr init, Expr cond, Expr incr, Stmt body) {
		super(pos);
		this.init = init;
		this.cond = cond;
		this.incr = incr;
		this.body = body;
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
