package IR.Instruction;

import Backend.Pass;
import IR.BasicBlock;
import IR.PointerRegister;
import IR.terminalStmt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class jump extends terminalStmt {
    public BasicBlock destination;
    public jump(BasicBlock destination) {
        super();
        this.destination = destination;
    }
    @Override public String toString() {return "goto " + destination.name;}

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
