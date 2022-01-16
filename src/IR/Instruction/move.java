package IR.Instruction;

import Backend.Pass;
import IR.Register;
import IR.Entity;
import IR.Statement;

public class move extends Statement {
    public Register reg;
    public Entity entity;
    public move(Register reg, Entity entity) {
        this.reg = reg;
        this.entity = entity;
    }

    @Override public String toString() {return reg + " = " + entity;}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}

}
