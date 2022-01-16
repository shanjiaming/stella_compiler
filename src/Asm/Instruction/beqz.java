package Asm.Instruction;


import Asm.AsmBasicBlock;
import Asm.AsmStmt;
import Asm.Reg;

public class beqz extends AsmStmt {
    public Reg op;
    public AsmBasicBlock trueBranch;
    public beqz(Reg op, AsmBasicBlock trueBranch) {
        this.op = op;
        this.trueBranch = trueBranch;
    }
    @Override public String toString() {return "bnez " + op + " " + trueBranch.name;}
}
