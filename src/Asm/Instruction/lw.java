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
		if(addr.globalName != null && addr.globalName.length() >= 4 && "str_".equals(addr.globalName.substring(0,4))){
			return "lui " + reg + ", %hi(" + addr.globalName + ")\n\taddi "+reg+", "+reg+", %lo(" + addr.globalName + ")";
		}
		else
		return "lw " + reg + ", " + addr;
	}

}
