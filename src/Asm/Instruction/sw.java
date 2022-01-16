package Asm.Instruction;

import Asm.Addr;
import Asm.AsmStmt;
import Asm.Reg;

public class sw extends AsmStmt {
	public Reg reg;
	public Addr addr;
	public sw(Reg reg, Addr addr) {
		this.reg = reg;
		this.addr = addr;
	}
	@Override public String toString() {
		return "sw " + reg + " " + addr;
	}

}