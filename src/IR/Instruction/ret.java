package IR.Instruction;

import IR.Entity;
import IR.Register;
import IR.terminalStmt;

public class ret extends terminalStmt {
    public ret() {

    }
    @Override public String toString() {return "return";}
}
