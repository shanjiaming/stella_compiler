package IR;

import java.util.*;

public class Function {
    public BasicBlock fnBasicBlock = new BasicBlock();
    public ArrayList<BasicBlock> basicBlocks = new ArrayList<>();


    public Function(String name, BasicBlock basicBlock) {
        basicBlock.name = name;
        fnBasicBlock = basicBlock;
        basicBlocks.add(basicBlock);
    }
}
