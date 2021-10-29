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
import Parser.MxLexer;
import Parser.MxParser;
import Util.MxErrorListener;
import Util.MxError.MxError;
import Util.globalScope;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;


public class Main {
    public static void main(String[] args) throws Exception{

        String name = "src/testcases/sema/basic-package/basic-1.mx";
        InputStream input = new FileInputStream(name);

        try {
            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parseTreeRoot = parser.program();
            globalScope gScope = new globalScope(null);
            ASTBuilder astBuilder = new ASTBuilder(gScope);
            Program ASTRoot = (Program)astBuilder.visit(parseTreeRoot);
//            new SymbolCollector(gScope).visit(ASTRoot);
//            new SemanticChecker(gScope).visit(ASTRoot);

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