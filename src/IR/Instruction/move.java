package IR.Instruction;

import Backend.Pass;
import IR.PointerRegister;
import IR.Statement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    @Override
    public Set<Integer> defs() {
        Set<Integer> ret = new HashSet<>();
        addIfIsVirtualRegister(ret, pdest);
        return ret;
    }

    @Override
    public Set<Integer> uses() {
        Set<Integer> ret = new HashSet<>();
        addIfIsVirtualRegister(ret, psrc);
        return ret;
    }

}
