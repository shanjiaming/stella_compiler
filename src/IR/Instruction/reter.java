package IR.Instruction;

import Backend.Pass;
import IR.Entity;
import IR.Register;
import IR.terminalStmt;

public class reter extends terminalStmt {
    public reter() {

    }
    @Override public String toString() {return "return";}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}
}
