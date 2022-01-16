package Asm.Instruction;

import Asm.AsmStmt;
import Asm.Reg;

public class lui extends AsmStmt {
    public Reg reg;
    public int inst;
    public lui(Reg reg, int inst) {
        this.reg = reg;
        this.inst = inst;
    }

    @Override public String toString() {return "lui " + reg + " " + inst;}

}
