package IR.Instruction;

import IR.*;

public class store extends Statement {
	public PointerRegister pointer;
	public Entity entity;
	public store(PointerRegister pointer, Entity entity) {
		super();
		this.pointer = pointer;
		this.entity = entity;
	}
	@Override public String toString() {
		return pointer.memToString() + " = " + entity;}
}
