package IR.Instruction;

import Backend.Pass;
import IR.Entity;
import IR.PointerRegister;
import IR.Register;
import IR.terminalStmt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class reter extends terminalStmt {
    public reter() {

    }
    @Override public String toString() {return "return";}

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
