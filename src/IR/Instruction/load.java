package IR.Instruction;

import Backend.Pass;
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
		if(pointer == null){
			System.out.println("ohno ini nullmem!");
		}
	}
	@Override public String toString() {
		if(pointer == null){
			System.out.println("nullmempointer");
		}
		return reg + " = " + pointer.memToString();
	}

	@Override
	public void accept(Pass visitor) {visitor.visit(this);}

}
