package Asm.Instruction;


import Asm.AsmBasicBlock;
import Asm.AsmStmt;
import Asm.Reg;

public class bnez extends AsmStmt {
    public Reg op;
    public String trueBranch;
    public bnez(Reg op, String trueBranch) {
        this.op = op;
        this.trueBranch = trueBranch;
    }
    @Override public String toString() {return "bnez " + op + ", " + trueBranch;}
}
