package Backend;

import Asm.Reg;
import IR.*;
import IR.Instruction.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FlowAnalyzer extends Pass {

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
        while (changed) {
            changed = false;
            for (int i = sz - 1; i >= 0; i--) {
                visit(function.basicBlocks.get(i));
            }
        }

        Map<Integer, Set<Integer>> contractmap = new HashMap<>();//注意加边的时候要加两遍

        Set<Integer> nodes = new HashSet<>();

        for (BasicBlock b : function.basicBlocks) {
            for (Statement s : b.stmts) {
                nodes.addAll(s.uses());
                nodes.addAll(s.defs());
            }
        }

        for (var node : nodes) {
            contractmap.put(node, new HashSet<>());
        }


        for (BasicBlock bl : function.basicBlocks) {
            for (Statement s : bl.stmts) {
                if (s instanceof move) {
                    for (var a : s.defs()) {
                        for (var b : s.outs) {
                            if (((move) s).psrc.offset == Register.s0 && b.equals(((move) s).psrc.address))
                                break;
                            contractmap.get(a).add(b);
                            contractmap.get(b).add(a);
                        }
                    }
                } else {
                    for (var a : s.defs()) {
                        for (var b : s.outs) {
                            contractmap.get(a).add(b);
                            contractmap.get(b).add(a);
                        }
                    }
                }
            }
        }

        Map <Integer, Integer> colormap = new HashMap<>();

        for(int i = 0; i < 11; ++i){
            ContinueFor : for (var node : nodes) {
                if(colormap.containsKey(node)) continue;
                var crashs = contractmap.get(node);
                for(var crash : crashs){
                    if(Integer.valueOf(i).equals(colormap.get(crash)))
                        continue ContinueFor;
                }
                colormap.put(node, i);
            }
        }

        //染色完毕，只等分配了


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
        for (Statement sm : stmt.goout) {
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
