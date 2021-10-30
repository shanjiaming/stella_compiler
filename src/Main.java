/**
 * @author JiamingShan
 */
//import AST.RootNode;
//import Assembly.AsmFn;
//import Backend.*;
import AST.Program;
import Frontend.ASTBuilder;
//import Frontend.SemanticChecker;
//import Frontend.SymbolCollector;
//import MIR.block;
//import MIR.mainFn;
import Frontend.SemanticChecker;
import Parser.MxLexer;
import Parser.MxParser;
import Util.MxErrorListener;
import Util.MxError.MxError;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);
        String testcaseName;
//        testcaseName = scanner.nextLine();
        testcaseName = "sema/function-package/function-4.mx";
        String name = "src/testcases/" + testcaseName;
        InputStream input = new FileInputStream(name);

        try {
            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parseTreeRoot = parser.program();
            ASTBuilder astBuilder = new ASTBuilder();
            Program ASTRoot = (Program)astBuilder.visit(parseTreeRoot);
//            new SymbolCollector(gScope).visit(ASTRoot);
            new SemanticChecker().visit(ASTRoot);

            /*mainFn f = new mainFn();
            new IRBuilder(f, gScope).visit(ASTRoot);
            // new IRPrinter(System.out).visitFn(f);

            AsmFn asmF = new AsmFn();
            new InstSelector(asmF).visitFn(f);
            new RegAlloc(asmF).work();
            new AsmPrinter(asmF, System.out).print();*/
        } catch (MxError er) {
            System.err.println(er.toString());
            throw new RuntimeException();
        }
    }
}