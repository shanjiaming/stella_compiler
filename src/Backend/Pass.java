package Backend;

import IR.BasicBlock;
import IR.Function;
import IR.IREntry;
import IR.Statement;

public abstract class Pass {

    private final IREntry programIREntry;


    public void visit(IREntry irEntry){}
    public void visit(Function function){}
    public void visit(BasicBlock basicBlock){}
    public void visit(Statement statement){}

    public void exitvisit(IREntry irEntry){}
    public void exitvisit(Function function){}

    void exitvisit(BasicBlock basicBlock){}




    Pass(IREntry programIREntry){this.programIREntry = programIREntry;}

    public void run() {
        visit(programIREntry);
        for (Function function : programIREntry.functions){
            visit(function);
            for (BasicBlock basicBlock : function.basicBlocks){
                visit(basicBlock);
                for (Statement statement: basicBlock.stmts()){
                    visit(statement);
                }
                exitvisit(basicBlock);
            }
            exitvisit(function);
        }
        exitvisit(programIREntry);
    }
}
