/**
 * @author JiamingShan
 */
//import AST.RootNode;
//import Assembly.AsmFn;
//import Backend.*;

import AST.Program;
import Asm.AsmEntry;
import Backend.*;
import Frontend.ASTBuilder;
//import Frontend.SemanticChecker;
//import Frontend.SymbolCollector;
//import MIR.block;
//import MIR.mainFn;
import Frontend.SemanticChecker;
import IR.IREntry;
import Parser.MxLexer;
import Parser.MxParser;
import Util.MxErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;


public class Main {
    public static void main(String[] args) throws Exception {
        boolean ir = false;//注：这个irbuilder已经被修改得在有全局变量的情况下irPrinter出来的东西无法工作了。残念。
        boolean oj = false;

//        Scanner scanner = new Scanner(System.in);
//        String testcaseName;
//        testcaseName = scanner.nextLine();
//        testcaseName = "sema\\misc-package\\misc-6.mx";
//        String name = "src/testcases/" + testcaseName;
//        InputStream input = new FileInputStream(name);
        InputStream input;
        if (ir || !oj) input = new FileInputStream("asm/test.mx");
        else input = System.in;
//        else input = new FileInputStream("asm/test.mx");
        OutputStream output;
//        OutputStream output = System.out;
        if (ir) output = new FileOutputStream("r/ir.cpp");
        else if (oj) output = new FileOutputStream("output.s");
        else output = new FileOutputStream("asm/test.s");//汇编语言输出到哪里

        try {
            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parseTreeRoot = parser.program();
            ASTBuilder astBuilder = new ASTBuilder();
            Program ASTRoot = (Program) astBuilder.visit(parseTreeRoot);
            new SemanticChecker().visit(ASTRoot);

            IREntry f = new IREntry();
            new IRBuilder(f).visit(ASTRoot);
            new FlowAnalyzer(f);

            if (!ir) {
                AsmEntry a = new AsmEntry();
                AsmBuilder asmBuilder = new AsmBuilder(f, a);
                new AsmPrinter(a, output).run();
                if (!oj) {
//                Runtime.getRuntime().exec("wsl cd asm && ./ravel --oj-mode").waitFor();
                    System.out.print(new String(Runtime.getRuntime().exec("wsl cd asm && ./ravel --oj-mode").getErrorStream().readAllBytes()));

                    System.out.print(new String(Runtime.getRuntime().exec("wsl cat asm/test.out").getInputStream().readAllBytes()));
                }
            } else {
                new IRPrinter(f, output).run();

//                System.out.print(new String(Runtime.getRuntime().exec("wsl g++ -o r/a.out r/ir.cpp").getErrorStream().readAllBytes()));
//                System.out.println(new String(Runtime.getRuntime().exec("wsl ./r/a.out < asm/test.in").getInputStream().readAllBytes()));
                Runtime.getRuntime().exec("wsl g++ -o r/a.out r/ir.cpp").waitFor();
                Runtime.getRuntime().exec("wsl ./r/a.out < asm/test.in > asm/test.out").waitFor();
                System.out.print(new String(Runtime.getRuntime().exec("wsl cat asm/test.out").getInputStream().readAllBytes()));

            }
//            Runtime.getRuntime().exec("wsl rm r/a.out");

//            System.out.println("Success");
        } catch (Exception er) {
//            System.err.println(er);
            er.printStackTrace();
            throw new RuntimeException();
//            System.out.println("Fail");
        }
    }
}