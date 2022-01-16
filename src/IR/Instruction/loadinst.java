package IR.Instruction;

import Backend.Pass;
import IR.Constant;
import IR.Entity;
import IR.Register;
import IR.Statement;

public class loadinst extends Statement {
    public Register reg;
    public Constant constant;
    public loadinst(Register reg, Constant constant) {
        this.reg = reg;
        this.constant = constant;
    }

    @Override public String toString() {return reg + " = " + constant;}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}

}
