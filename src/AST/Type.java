package AST;

import Util.position;

public class Type extends ASTNode {
	public String typeName;
	public int dim;

	public Type(position pos, String typeName, int dim) {
		super(pos);
		this.typeName = typeName;
		this.dim = dim;
 	}

	@Override
	public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
