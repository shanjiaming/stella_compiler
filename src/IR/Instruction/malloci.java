package IR.Instruction;

import Backend.Pass;
import IR.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class malloci extends Statement {

    public PointerRegister pointer;

    public int length;

    public malloci(PointerRegister pointer, int length) {
        this.pointer = pointer;
        this.length = length;
    }
    @Override public String toString() {return "ir_malloc(" + pointer + ", " + length + ")";}

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
