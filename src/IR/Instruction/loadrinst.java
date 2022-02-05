package IR.Instruction;

import Backend.Pass;
import IR.PointerRegister;
import IR.Register;
import IR.Statement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class loadrinst extends Statement {
    public Register reg;
    public int constant;
    public loadrinst(Register reg, int constant) {
        this.reg = reg;
        this.constant = constant;
    }

    @Override public String toString() {return reg + " = " + constant;}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}

    @Override
    public Set<Integer> defs() {
        Set<Integer> ret = new HashSet<>();
        return ret;
    }

    @Override
    public Set<Integer> uses() {
        Set<Integer> ret = new HashSet<>();
        return ret;
    }

}
