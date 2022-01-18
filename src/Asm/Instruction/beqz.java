package Asm.Instruction;


import Asm.AsmBasicBlock;
import Asm.AsmStmt;
import Asm.Reg;

public class beqz extends AsmStmt {
    public Reg op;
    public String trueBranch;
    public beqz(Reg op, String trueBranch) {
        this.op = op;
        this.trueBranch = trueBranch;
    }
    @Override public String toString() {return "beqz " + op + ", " + trueBranch;}
}
