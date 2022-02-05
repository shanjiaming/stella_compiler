package IR.Instruction;

import Backend.Pass;
import IR.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class binary extends Statement {

    public PointerRegister lhs;
    public PointerRegister op1, op2;
    public String op;

    public binary(PointerRegister lhs, PointerRegister op1, PointerRegister op2, String op) {
        super();
        this.lhs = lhs;
        this.op1 = op1;
        this.op2 = op2;
        this.op = op;
    }

    @Override
    public String toString() {
        return lhs + " = " + op1 + op + op2;
    }

    @Override
    public void accept(Pass visitor) {
        visitor.visit(this);
    }

    @Override
    public Set<Integer> defs() {
        Set<Integer> ret = new HashSet<>();
        addIfIsVirtualRegister(ret, lhs);
        return ret;
    }

    @Override
    public Set<Integer> uses() {
        Set<Integer> ret = new HashSet<>();
        addIfIsVirtualRegister(ret, op1);
        addIfIsVirtualRegister(ret, op2);
        return ret;
    }
}
