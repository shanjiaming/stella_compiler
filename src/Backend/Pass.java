package Backend;

import IR.BasicBlock;
import IR.Function;
import IR.IREntry;
import IR.Instruction.*;
import IR.Statement;

public abstract class Pass {

    private final IREntry programIREntry;

    public Pass(IREntry irEntry) {
        programIREntry = irEntry;
    }

    abstract public void visit(Function function);
    abstract public void visit(BasicBlock basicBlock);
    abstract public void visit(addri it);
    abstract public void visit(binary it);
    abstract public void visit(binaryi it);
    abstract public void visit(branch it);
    abstract public void visit(callfunc it);
    abstract public void visit(jump it);
    abstract public void visit(load it);
    abstract public void visit(loadinst it);
    abstract public void visit(loadrinst it);
    abstract public void visit(malloci it);
    abstract public void visit(move it);
    abstract public void visit(reter it);
    abstract public void visit(store it);
    abstract public void visit(blockfirst it);
}
