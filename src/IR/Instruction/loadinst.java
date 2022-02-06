package IR.Instruction;

import Backend.Pass;
import IR.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class loadinst extends Statement {
    public PointerRegister pointer;
    public int constant;
    public loadinst(PointerRegister pointer, int constant) {
        this.pointer = pointer;
        this.constant = constant;
    }

    @Override public String toString() {return pointer + " = " + constant;}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}

    @Override
    public Set<Integer> defs() {
        Set<Integer> ret = new HashSet<>();
        addIfIsVirtualRegister(ret, pointer);
        return ret;
    }

    @Override
    public Set<Integer> uses() {
        Set<Integer> ret = new HashSet<>();
        return ret;
    }

}
