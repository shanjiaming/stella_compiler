package Backend;

import Asm.Reg;
import IR.*;
import IR.Instruction.*;

import java.util.*;

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
                            if (b.equals(((move) s).psrc.address))
                                continue;
                            if (a.equals(b)) continue;
                            contractmap.get(a).add(b);
                            contractmap.get(b).add(a);
                        }
                    }
                } else {
                    for (var a : s.defs()) {
                        for (var b : s.outs) {
                            if (a.equals(b)) continue;
                            contractmap.get(a).add(b);
                            contractmap.get(b).add(a);
                        }
                    }
                }
            }
        }


        Map<Integer, Integer> colormap = new HashMap<>();

//        ArrayList<Integer> nodess = new ArrayList<>(nodes);
//
//        Collections.sort(nodess, new Comparator<Integer>() {
//
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                int x = contractmap.get(o1).size() - contractmap.get(o2).size();
//                if (x > 0) return 1;
//                if (x < 0) return -1;
//                return 0;
//            }
//        });
//
//        boolean[] sxisused = new boolean[Register.ssSIZE];
//        for (int i = 0; i < Register.ssSIZE; ++i) {
//            sxisused[i] = false;
//            ContinueFor:
//            for (var node : nodess) {
//                if (colormap.containsKey(node)) continue;
//                var crashs = contractmap.get(node);
//                for (var crash : crashs) {
//                    if (Integer.valueOf(i).equals(colormap.get(crash)))
//                        continue ContinueFor;
//                }
//                colormap.put(node, i);
//                sxisused[i] = true;
//            }
//        }

//        int colored = 0;
//        int notcolored = 0;
//
//        for(var node : nodes){
//            if(colormap.containsKey(node)){
//                colored++;
//            }else {
//                notcolored++;
//            }
//        }
//        System.out.println("colored = " + colored + " not colored = "+ notcolored);

        Stack<Integer> stk = new Stack<>();

        Map<Integer, Set<Integer>> copygragh = new HashMap<>();

        for (var k : nodes) {
            Set<Integer> s = new HashSet<>();
            Set<Integer> v = contractmap.get(k);
            for (var i : v) {
                s.add(i);
            }
            copygragh.put(k, s);
        }


        Iterator<Integer> iterator;
        while (!copygragh.isEmpty()) {
            var ks = copygragh.keySet();
            boolean flag = false;
            iterator = ks.iterator();
//            int mindegree = 100000;
            while (iterator.hasNext()) {
                var k = iterator.next();
                Set<Integer> gk = copygragh.get(k);
                if (gk.size() < Register.ssSIZE) {
                    stk.add(k);
                    for (var i : gk) {
                        copygragh.get(i).remove(k);
                    }
                    iterator.remove();
                    flag = true;
                }
//                mindegree = Math.min(mindegree, gk.size());
            }
            if (!flag) {
//                for (int ii = 0; ii < mindegree - (Register.ssSIZE - 1); ++ii) {
                    iterator = ks.iterator();
                    var k = iterator.next();
                    Set<Integer> gk = copygragh.get(k);
                    stk.add(k);
                    for (var i : gk) {
                        copygragh.get(i).remove(k);
                    }
                    iterator.remove();
//                }
            }
        }

        boolean[] sxisused = new boolean[Register.ssSIZE];
        for (int i = 0; i < Register.ssSIZE; ++i) {
            sxisused[i] = false;
        }

        while (!stk.isEmpty()) {
            var k = stk.pop();
            boolean[] barray = new boolean[Register.ssSIZE];
            for (int i = 0; i < Register.ssSIZE; ++i) {
                barray[i] = true;
            }
            for (var v : contractmap.get(k)) {
                if (colormap.containsKey(v)) barray[colormap.get(v)] = false;
            }
            for (int i = 0; i < Register.ssSIZE; ++i) {
                if (barray[i]) {
                    colormap.put(k, i);
                    sxisused[i] = true;
                    break;
                }
            }
        }


//        colormap = new HashMap<>();

        function.colormap = colormap;
        function.sxisused = sxisused;


        boolean nocall = true;
        BFunc:
        for (BasicBlock b : function.basicBlocks) {
            for (Statement s : b.stmts) {
                if (s instanceof callfunc || s instanceof malloci) {
                    nocall = false;
                    break BFunc;
                }
            }
        }
        function.nocall = nocall;


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
