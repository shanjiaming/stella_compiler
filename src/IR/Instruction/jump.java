package IR.Instruction;

import IR.BasicBlock;
import IR.terminalStmt;

public class jump extends terminalStmt {
    public BasicBlock destination;
    public jump(BasicBlock destination) {
        super();
        this.destination = destination;
    }
    @Override public String toString() {return "goto " + destination.name;}
}
