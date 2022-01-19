package Asm.Instruction;

import Asm.AsmStmt;
import IR.BasicBlock;

public class j extends AsmStmt {
    public String destination;
    public j(String destination) {
        this.destination = destination;
    }
    @Override public String toString() {return "j " + destination;}
}
