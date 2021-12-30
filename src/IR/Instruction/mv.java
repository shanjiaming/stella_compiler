package IR.Instruction;

import IR.Register;
import IR.Entity;
import IR.Statement;

public class mv extends Statement {
    public Register reg;
    public Entity entity;
    public mv(Register reg, Entity entity) {
        this.reg = reg;
        this.entity = entity;
    }

    @Override public String toString() {return reg + " = " + entity;}

}
