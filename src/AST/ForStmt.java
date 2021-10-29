package AST;

//import LLVMIR.basicBlock;
import Util.position;

public class ForStmt extends Stmt {
	public Expr init = null, cond = null, incr = null;
	public Stmt stmt;

//	public basicBlock destBlock, incrBlock;


	public ForStmt(position pos, Expr init, Expr cond, Expr incr, Stmt stmt) {
		super(pos);
		this.init = init;
		this.cond = cond;
		this.incr = incr;
		this.stmt = stmt;
	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
