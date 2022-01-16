package Asm;

import java.util.ArrayList;

public class AsmFunction {
    public AsmBasicBlock fnAsmBasicBlock = new AsmBasicBlock();
    public ArrayList<AsmBasicBlock> asmBasicBlocks = new ArrayList<>();
    public String name;


    public AsmFunction(String name, AsmBasicBlock asmBasicBlock) {
        asmBasicBlock.name = name;
        fnAsmBasicBlock = asmBasicBlock;
        asmBasicBlocks.add(asmBasicBlock);
    }
}
