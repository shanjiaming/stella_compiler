package IR.Instruction;

import Backend.Pass;
import IR.*;

public class malloci extends Statement {

    public PointerRegister register;

    public int length;

    public malloci(PointerRegister register, int length) {
        this.register = register;
        this.length = length;
    }
    @Override public String toString() {return "ir_malloc(" + register + ", " + length + ")";}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}

}
