package IR.Instruction;

import Backend.Pass;
import IR.*;

public class loadinst extends Statement {
    public PointerRegister reg;
    public int constant;
    public loadinst(PointerRegister reg, int constant) {
        this.reg = reg;
        this.constant = constant;
    }

    @Override public String toString() {return reg + " = " + constant;}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}

}
