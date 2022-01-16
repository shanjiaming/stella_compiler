package Backend;

import Asm.*;

public abstract class AsmPass {

    private final AsmEntry programAsmEntry;


    public void visit(AsmEntry AsmEntry){}
    public void visit(AsmFunction function){}
    public void visit(AsmBasicBlock basicBlock){}
    public void visit(AsmStmt statement){}

    public void exitvisit(AsmEntry AsmEntry){}
    public void exitvisit(AsmFunction function){}

    void exitvisit(AsmBasicBlock basicBlock){}




    AsmPass(AsmEntry programAsmEntry){this.programAsmEntry = programAsmEntry;}

    public void run() {
        visit(programAsmEntry);
        for (AsmFunction function : programAsmEntry.functions){
            visit(function);
            for (AsmBasicBlock basicBlock : function.asmBasicBlocks){
                visit(basicBlock);
                for (AsmStmt statement: basicBlock.stmts()){
                    visit(statement);
                }
                exitvisit(basicBlock);
            }
            exitvisit(function);
        }
        exitvisit(programAsmEntry);
    }
}
