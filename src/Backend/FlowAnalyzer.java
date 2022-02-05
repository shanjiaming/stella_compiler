package Backend;

import IR.BasicBlock;
import IR.Function;
import IR.IREntry;
import IR.Instruction.*;
import IR.Statement;

import java.util.HashSet;
import java.util.Set;

public class FlowAnalyzer extends Pass{

    public FlowAnalyzer(IREntry irEntry) {
        super(irEntry);
        for (var i : irEntry.functions) {
            visit(i);
        }
    }

    private boolean changed = true;

    @Override
    public void visit(Function function) {
        int sz = function.basicBlocks.size();

        changed = true;
        while(changed) {
            changed = false;
            for (int i = sz - 1; i >= 0; i--) {
                visit(function.basicBlocks.get(i));
            }
        }
    }

    @Override
    public void visit(BasicBlock basicBlock) {
        int sz = basicBlock.stmts.size();
        for (int i = sz - 1; i >= 0; i--) {
//            basicBlock.stmts.get(i).accept(this);
            visitstmt(basicBlock.stmts.get(i));

        }
        visitstmt(basicBlock.blockfirststmt);
    }

    private void visitstmt(Statement stmt) {
        Set<Integer> outshat = new HashSet<>();
        for(Statement sm : stmt.goin){
            outshat.addAll(sm.ins);
        }
        changed |= !stmt.outs.equals(outshat);
        stmt.outs = outshat;

        Set<Integer> inshat = new HashSet<>();
        inshat.addAll(stmt.outs);
        inshat.removeAll(stmt.defs());
        inshat.addAll(stmt.uses());
        changed |= !stmt.ins.equals(inshat);
        stmt.ins = inshat;
//        stmt.ins <- stmt.uses() + (stmt.outs - stmt.defs());
//        stmt.outs = stmt.goout.forEach(sm->sm.ins);
    }



    @Override
    public void visit(addri it) {

    }

    @Override
    public void visit(binary it) {

    }

    @Override
    public void visit(binaryi it) {

    }

    @Override
    public void visit(branch it) {

    }

    @Override
    public void visit(callfunc it) {

    }

    @Override
    public void visit(jump it) {

    }

    @Override
    public void visit(load it) {

    }

    @Override
    public void visit(loadinst it) {

    }

    @Override
    public void visit(loadrinst it) {

    }

    @Override
    public void visit(malloci it) {

    }

    @Override
    public void visit(move it) {

    }

    @Override
    public void visit(reter it) {

    }

    @Override
    public void visit(store it) {

    }

    @Override
    public void visit(blockfirst it) {

    }

}
