package IR.Instruction;

import Backend.Pass;
import IR.*;

public class malloc extends Statement {

    public Register register;

    public Entity length;

    public malloc(Register register, Entity length) {
        this.register = register;
        this.length = length;
    }
    public malloc(Register register, int length) {
        this.register = register;
        this.length = new Constant(length);
    }

    @Override public String toString() {return "ir_malloc(" + register + ", " + length + ")";}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}

}
