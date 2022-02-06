package IR.Instruction;

import Backend.Pass;
import IR.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class store extends Statement {
	public PointerRegister pointer;
	public Register reg;
	public int removeNumber = -100;
	public store(PointerRegister pointer, Register reg) {
		super();
		this.pointer = pointer;
		this.reg = reg;
	}
	public store(PointerRegister pointer, Register reg, int rm) {
		super();
		this.pointer = pointer;
		this.reg = reg;
		this.removeNumber = rm;
	}
	@Override public String toString() {
		return pointer + " = " + reg;}

	@Override
	public void accept(Pass visitor) {visitor.visit(this);}

	@Override
	public Set<Integer> defs() {
		Set<Integer> ret = new HashSet<>();
		addIfIsVirtualRegister(ret, pointer);
		return ret;
	}

	@Override
	public Set<Integer> uses() {
		Set<Integer> ret = new HashSet<>();
		return ret;
	}
}
