package IR.Instruction;

import Backend.Pass;
import IR.PointerRegister;
import IR.Statement;

public class move extends Statement {
    public PointerRegister pdest;
    public PointerRegister psrc;
    public move(PointerRegister pdest, PointerRegister psrc) {
        this.pdest = pdest;
        this.psrc = psrc;
    }

    @Override public String toString() {return pdest + " = " + psrc;}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}

}
