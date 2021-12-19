package IR.Instruction;

import IR.Constant;
import IR.PointerRegister;
import IR.Statement;

public class malloc extends Statement {

    public PointerRegister pointerRegister;

    public int length;

    public malloc(PointerRegister pointerRegister, int length) {
        this.pointerRegister = pointerRegister;
        this.length = length;
    }

    @Override public String toString() {return "ir_malloc(" + pointerRegister + ", " + length + ")";}

}
