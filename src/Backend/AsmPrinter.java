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
    public void visit(AsmEntry AsmEntry) {
        println("\t.text");
    }

    @Override
    public void visit(AsmFunction function) {
        println("\t.globl " + function.name + "\t\t\t\t\t\t## -- Begin function " + function.name);
    }

    @Override
    public void visit(AsmBasicBlock basicBlock) {
        println("\t" + basicBlock.name + " :");
    }

    @Override
    public void visit(AsmStmt statement) {
        println(statement.toString());
    }

    @Override
    public void exitvisit(AsmEntry AsmEntry) {
        super.exitvisit(AsmEntry);
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
