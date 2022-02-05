package IR;

import java.util.*;

public class Function {
    public BasicBlock fnBasicBlock = new BasicBlock();
    public ArrayList<BasicBlock> basicBlocks = new ArrayList<>();
    public Map<Integer, Set<Integer>> contractmap = new HashMap<>();//注意加边的时候要加两遍


    public Function(String name, BasicBlock basicBlock) {
        basicBlock.name = name;
        fnBasicBlock = basicBlock;
        basicBlocks.add(basicBlock);
    }
}
