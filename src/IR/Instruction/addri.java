package IR.Instruction;

import Backend.Pass;
import IR.PointerRegister;
import IR.Register;
import IR.Statement;

public class addri extends Statement {

    public Register lhs;
    public Register op1;
    public int op2;
    public addri(Register lhs, Register op1, int op2) {
        super();
        this.lhs = lhs;
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override public String toString() {return lhs + " = " + op1 + "+" + op2;}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}
}
