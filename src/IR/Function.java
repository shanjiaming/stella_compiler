package IR;

import java.util.ArrayList;
import java.util.Set;

public class Function {
    public BasicBlock fnBasicBlock = new BasicBlock();
    public ArrayList<BasicBlock> basicBlocks = new ArrayList<>();


    public Function(String name, BasicBlock basicBlock) {
        basicBlock.name = name;
        fnBasicBlock = basicBlock;
        basicBlocks.add(basicBlock);
    }
}
