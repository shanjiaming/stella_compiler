/**
 * @author JiamingShan
 */
//import AST.RootNode;
//import Assembly.AsmFn;
//import Backend.*;

import AST.Program;
import Backend.IRBuilder;
import Backend.IRPrinter;
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

//        Scanner scanner = new Scanner(System.in);
//        String testcaseName;
//        testcaseName = scanner.nextLine();
//        testcaseName = "sema\\misc-package\\misc-6.mx";
//        String name = "src/testcases/" + testcaseName;
//        InputStream input = new FileInputStream(name);
//        InputStream input = System.in;
//        OutputStream output = System.out;
        InputStream input = new FileInputStream("r/myinput.mx");
        OutputStream output = new FileOutputStream("r/myoutput.cpp");//中间语言输出到哪里

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
            new IRPrinter(f, output).run();

            output.close();

            Runtime.getRuntime().exec("wsl g++ -o r/a.out r/myoutput.cpp").waitFor();//这一句莫名其妙没跑起来
            Runtime.getRuntime().exec("wsl ./r/a.out > r/ans.txt").waitFor();
            System.out.println(new String(new FileInputStream("r/ans.txt").readAllBytes()));//中间语言验证结果输出到哪里
            /*AsmFn asmF = new AsmFn();
            new InstSelector(asmF).visitFn(f);
            new RegAlloc(asmF).work();
            new AsmPrinter(asmF, System.out).print();*/
//            System.out.println("Success");
        } catch (Exception er) {
//            System.err.println(er);
            er.printStackTrace();
            throw new RuntimeException();
//            System.out.println("Fail");
        }
    }
}