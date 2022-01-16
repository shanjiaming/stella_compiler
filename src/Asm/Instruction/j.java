package Asm.Instruction;

import Asm.AsmStmt;
import IR.BasicBlock;

public class j extends AsmStmt {
    public BasicBlock destination;
    public j(BasicBlock destination) {
        this.destination = destination;
    }
    @Override public String toString() {return "j " + destination.name;}
}
