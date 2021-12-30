package IR.Instruction;

import IR.Entity;
import IR.PointerRegister;
import IR.Register;
import IR.Statement;

public class load extends Statement {
	public PointerRegister pointer;
	public Register reg;
	public load(PointerRegister pointer, Register reg) {
		super();
		this.pointer = pointer;
		this.reg = reg;
		assert (pointer != null);
		assert (reg != null);
	}
	@Override public String toString() {return reg + " = " + pointer.memToString();}

}
