package IR.Instruction;

import Backend.Pass;
import IR.BasicBlock;
import IR.PointerRegister;
import IR.terminalStmt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class branch extends terminalStmt {
    public PointerRegister op;
    public BasicBlock trueBranch, falseBranch;

    public branch(PointerRegister op, BasicBlock trueBranch, BasicBlock falseBranch) {
        super();
        this.op = op;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    @Override
    public String toString() {
        return "if(" + op + ") goto " + trueBranch.name + "; else goto " + falseBranch.name;
    }

    @Override
    public void accept(Pass visitor) {
        visitor.visit(this);
    }

    @Override
    public Set<Integer> defs() {
        Set<Integer> ret = new HashSet<>();
        return ret;
    }

    @Override
    public Set<Integer> uses() {
        Set<Integer> ret = new HashSet<>();
        addIfIsVirtualRegister(ret, op);
        return ret;
    }
}
