package IR.Instruction;

import IR.Entity;
import IR.Register;
import IR.terminalStmt;

public class ret extends terminalStmt {
    public Entity entity;
    public ret(Entity entity) {
        this.entity = entity;
    }
//    @Override public String toString() {return "return " + entity;}
    @Override public String toString() {return "return";}
}
