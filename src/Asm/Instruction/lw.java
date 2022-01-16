package Asm.Instruction;

import Asm.Addr;
import Asm.AsmStmt;
import Asm.Reg;

public class lw extends AsmStmt {
	public Reg reg;
	public Addr addr;
	public lw(Reg reg, Addr addr) {
		super();
		this.reg = reg;
		this.addr = addr;
	}
	@Override public String toString() {
		return "lw " + reg + " " + addr;
	}

}
