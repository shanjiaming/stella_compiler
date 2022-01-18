package Asm.Instruction;

import Asm.AsmStmt;
import Asm.Reg;
import IR.Constant;
import IR.Register;

public class li extends AsmStmt {
    public Reg reg;
    public int inst;
    public li(Reg reg, int inst) {
        this.reg = reg;
        this.inst = inst;
    }

    @Override public String toString() {return "li " + reg + ", " + inst;}

}
