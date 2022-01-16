package IR.Instruction;

import Backend.Pass;
import IR.BasicBlock;
import IR.terminalStmt;

public class jump extends terminalStmt {
    public BasicBlock destination;
    public jump(BasicBlock destination) {
        super();
        this.destination = destination;
    }
    @Override public String toString() {return "goto " + destination.name;}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}
}
