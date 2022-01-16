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

    private void println() {
        outstr.append("\n");
    }

    private void println(String s) {
        outstr.append(s).append("\n");
    }

    @Override
    public void visit(IREntry irEntry) {
    }

    @Override
    public void exitvisit(IREntry irEntry) {
        StringBuilder result = new StringBuilder("""
                #include <iostream>
                #include <string>
                #include <vector>
                #include <unordered_map>

                std::unordered_map<int, int> mem;
                std::unordered_map<int, int> reg;
                std::vector<std::string> stringpool;

                #define para1 (mem[-16+reg[/*sp*/0]])
                #define para2 (mem[-20+reg[/*sp*/0]])
                #define para3 (mem[-24+reg[/*sp*/0]])
                #define retval reg[/*a0*/3]

                void ir_malloc(int &pointerReg, int length) {
                    static int ptr = 100000000;
                    pointerReg = ptr;
                    for (int i = ptr; i < ptr + length; i += 4) {
                        mem[i] = 0;
                    }
                    ptr += length;
                }

                void ifake(int p, int& ret, int i){
                    if (i > 4 * mem[p]) return;
                    int sz = mem[p + i];
                    int sz4 = 4 * sz;
                    ir_malloc(ret,sz4 + 4);
                    ret += 4;
                    mem[ret - 4] = sz;
                    for (int j = 0; j < sz4; j+=4)
                        ifake(p,mem[ret+j], i+4);
                }

                void ir_new_array(){
                    ifake(para1,para2,para3);
                }

                void print() {
                    std::cout << stringpool[para1] << std::flush;
                }

                void println() {
                    std::cout << stringpool[para1] << std::endl;
                }

                void getInt() {
                    int n;
                    std::cin >> n;
                    retval = n;
                }

                void getString() {
                    std::string n;
                    std::cin >> n;
                    stringpool.push_back(n);
                    retval = stringpool.size() - 1;
                }

                void toString() {
                    std::string n = std::to_string(para1);
                    stringpool.push_back(n);
                    retval = stringpool.size() - 1;
                }

                void printInt() {
                    std::cout << para1 << std::flush;
                }

                void printlnInt() {
                    std::cout << para1 << std::endl;
                }

                void string__length() {
                    retval = stringpool[para1].size();
                }

                void string__substring() {
                    stringpool.push_back(stringpool[para3].substr(para1, para2 - para1));
                    retval = stringpool.size() - 1;
                }

                void string__parseInt() {

                    for (std::string str = stringpool[para1]; !str.empty(); str.pop_back()) {
                        try {
                            retval = stoi(str);
                            return;
                        } catch (...) {

                        }
                    }
                }

                void string__ord() {
                    retval = stringpool[para2][para1];
                }

                void string__add() {
                    stringpool.push_back(stringpool[para1] + stringpool[para2]);
                    retval = stringpool.size() - 1;
                }

                void string__eq() {
                    retval = (stringpool[para1] == stringpool[para2]);
                }

                void string__neq() {
                    retval = (stringpool[para1] != stringpool[para2]);
                }

                void string__l() {
                    retval = (stringpool[para1] < stringpool[para2]);
                }

                void string__g() {
                    retval = (stringpool[para1] > stringpool[para2]);
                }

                void string__leq() {
                    retval = (stringpool[para1] <= stringpool[para2]);
                }

                void string__geq() {
                    retval = (stringpool[para1] >= stringpool[para2]);
                }

                void array__size() {
                    retval = mem[para1 - 4];
                }
                """);
        result.append(funcDeclare).append("\n\n");
        result.append(outstr);
        result = new StringBuilder(result.toString().replace("main", "main_"));
        result.append("int main(){\n");
        int i = 0;
        for (var str : irEntry.stringpool) {
            result.append("\tstringpool.push_back(").append(str).append(");\n");
//            result.append("\tstringpool[").append(i++).append("] = ").append(str).append(";\n");
        }
        result.append("\tinitialize();\n}");
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
        String stmtstr = statement.toString() + ";";
        StringBuilder sb = new StringBuilder();
        int space = stmtstr.indexOf("=");
        for (int i = 0; i < 30 - space; ++i) sb.append(" ");
        sb.append(stmtstr);
        for (int i = 0; i < 30 + space - stmtstr.length(); ++i) sb.append(" ");
        println(sb.toString() + "               \t//" + statement.getClass().getSimpleName());
//        println("\t\t" + stmtstr +  "               \t//" + statement.getClass().getName());
    }


}
