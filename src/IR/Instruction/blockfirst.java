package IR.Instruction;

import Backend.Pass;
import IR.PointerRegister;
import IR.terminalStmt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class blockfirst extends terminalStmt {
    public blockfirst() {

    }
    @Override public String toString() {return "blockfirst";}

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
