package AST;

import Util.position;

abstract public class Stmt extends ASTNode {
	public Stmt() {}
	public Stmt(position pos) {super(pos);}
}
