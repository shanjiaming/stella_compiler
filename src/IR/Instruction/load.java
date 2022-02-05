package IR.Instruction;

import Backend.Pass;
import IR.PointerRegister;
import IR.Register;
import IR.Statement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
		return reg + " = " + pointer;
	}

	@Override
	public void accept(Pass visitor) {visitor.visit(this);}


	@Override
	public Set<Integer> defs() {
		Set<Integer> ret = new HashSet<>();
		return ret;
	}

	@Override
	public Set<Integer> uses() {
		Set<Integer> ret = new HashSet<>();
		addIfIsVirtualRegister(ret, pointer);
		return ret;
	}

}
