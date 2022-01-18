package IR.Instruction;

import Backend.Pass;
import IR.*;

public class store extends Statement {
	public PointerRegister pointer;
	public Register reg;
	public store(PointerRegister pointer, Register reg) {
		super();
		this.pointer = pointer;
		this.reg = reg;
	}
	@Override public String toString() {
		return pointer + " = " + reg;}

	@Override
	public void accept(Pass visitor) {visitor.visit(this);}
}
