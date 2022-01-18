package IR.Instruction;

import Backend.Pass;
import IR.BasicBlock;
import IR.Entity;
import IR.PointerRegister;
import IR.terminalStmt;

public class branch extends terminalStmt {
    public PointerRegister op;
    public BasicBlock trueBranch, falseBranch;
    public branch(PointerRegister op, BasicBlock trueBranch, BasicBlock falseBranch) {
        super();
        this.op = op;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }
    @Override public String toString() {return "if(" + op + ") goto " + trueBranch.name + "; else goto " + falseBranch.name;}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}
}
