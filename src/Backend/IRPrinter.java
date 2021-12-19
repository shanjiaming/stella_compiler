package Backend;

import IR.BasicBlock;
import IR.Function;
import IR.IREntry;
import IR.Statement;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class IRPrinter extends Pass {
    private final PrintWriter pWriter;
    OutputStream out;
    String funcDeclare = "";
    StringBuilder outstr = new StringBuilder();

    public IRPrinter(IREntry programIREntry, OutputStream out) {
        super(programIREntry);
        this.out = out;
        pWriter = new PrintWriter(out);
    }

    private void println(){
        outstr .append("\n") ;
    }

    private void println(String s){
        outstr .append(s+"\n") ;
    }

    @Override
    public void visit(IREntry irEntry) {
    }

    @Override
    public void exitvisit(IREntry irEntry) {
        String result = """
                #include <iostream>
                #include <unordered_map>
                using namespace std;
                unordered_map<int, int> mem;
                unordered_map<int, int> reg;
                       
                void ir_malloc (int& pointerReg, int length){
                    static int ptr = 100000000;
                    pointerReg = ptr;
                    ptr += length;
                }
                                
                void ir_alloca (int& pointerReg, int length){
                                
                }
                
                void printInt(int x){
                    cout << x;
                }
                void printlnInt(int x){
                    cout << x << endl;
                }
                                
                """;
        result += funcDeclare + "\n\n";
        result += outstr;
        result = result.replace("main", "main_");
        result += "int main(){ initialize(); }";
        pWriter.println(result);
        pWriter.flush();
    }

    @Override
    public void visit(BasicBlock basicBlock) {
        println("\t" + basicBlock.name + " :");
    }

    @Override
    public void exitvisit(BasicBlock basicBlock) {
        println();
    }

    @Override
    public void visit(Function function) {
        funcDeclare += "void " + function.fnBasicBlock.name + "();\n";
        println("void " + function.fnBasicBlock.name + "(){");
    }

    @Override
    public void exitvisit(Function function) {
        println("}\n");
    }

    @Override
    public void visit(Statement statement) {
        println("\t\t" + statement.toString() + ";" +  "               \t//" + statement.getClass().getName());
    }


}
