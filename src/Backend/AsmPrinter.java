package Backend;

import Asm.AsmBasicBlock;
import Asm.AsmEntry;
import Asm.AsmFunction;
import Asm.AsmStmt;
import IR.IREntry;

import java.io.OutputStream;
import java.io.PrintWriter;

public class AsmPrinter extends AsmPass {
    public AsmEntry asmEntry;
    private final PrintWriter pWriter;
    OutputStream out;
    String funcDeclare = "";
    StringBuilder outstr = new StringBuilder();

    public AsmPrinter(AsmEntry programAsmEntry, OutputStream out) {
        super(programAsmEntry);
        this.out = out;
        pWriter = new PrintWriter(out);
    }


    private void print(String s) {
        outstr.append(s);
    }

    private void println() {
        outstr.append("\n");
    }

    private void println(String s) {
        outstr.append(s).append("\n");
    }



    @Override
    public void visit(AsmEntry asmEntry) {
        println("\t.text\n");

        int i = 0;
        for(var s : asmEntry.globalpool){
            println("\t.type\t"+s+",@object");
            println("\t.comm\t"+s+",4,4");
            println();
            ++i;
        }
        println();
    }

    @Override
    public void visit(AsmFunction function) {
        println("\t.globl " + function.asmBasicBlocks.get(0).name + "\t\t\t\t\t\t# -- Begin function " + function.asmBasicBlocks.get(0).name);
    }

    @Override
    public void visit(AsmBasicBlock basicBlock) {
        println(basicBlock.name + ":");
    }

    @Override
    public void visit(AsmStmt statement) {
        println("\t" + statement.toString());
    }

    @Override
    public void exitvisit(AsmEntry asmEntry) {
        int i = 0;
        println("\t.section\t.rodata.str1.1,\"aMS\",@progbits,1");
        for(var s : asmEntry.stringpool){
            println("str_" + i + ":");
            println("\t.asciz\t" + s);
            println();
            ++i;
        }

        pWriter.println(outstr);
        pWriter.flush();
    }

    @Override
    public void exitvisit(AsmFunction function) {
        println();
    }

    @Override
    void exitvisit(AsmBasicBlock basicBlock) {
        println();
    }


}
