package IR.Instruction;

import IR.Constant;
import IR.Entity;
import IR.Register;
import IR.Statement;

public class li extends Statement {
    public Register reg;
    public Constant constant;
    public li(Register reg, Constant constant) {
        this.reg = reg;
        this.constant = constant;
    }

    @Override public String toString() {return reg + " = " + constant;}

}
