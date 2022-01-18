package Asm.Instruction;

import Asm.AsmStmt;
import Asm.Reg;
import IR.Entity;
import IR.Register;

public class mv extends AsmStmt {
    public Reg dest;
    public Reg reg;
    public mv(Reg dest, Reg reg) {
        this.dest = dest;
        this.reg = reg;
    }

    @Override public String toString() {return "mv " + dest + ", " + reg;}

}
