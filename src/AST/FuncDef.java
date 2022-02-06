package AST;

import Backend.IRBuilder;
import IR.PointerRegister;
import Util.position;
import Util.Type;

import java.util.ArrayList;
import java.util.Arrays;

public class FuncDef extends ASTNode implements ProgramUnit{
    public Type returnType;
    public ArrayList<Type> parameterTypes = new ArrayList<>();
    public ArrayList<String> parameterIdentifiers = new ArrayList<>();
    public SuiteStmt body;
    public String name;
    public ArrayList<PointerRegister> vars = new ArrayList<>();
    public static int  frameSize = IRBuilder.STACKSTARTSIZE;

    public FuncDef(String name, SuiteStmt body, Type returnType) {
        this.name = name;
        this.body = body;
        this.returnType = returnType;
    }
    public FuncDef(position pos, String name, SuiteStmt body, Type returnType) {
        super(pos);
        this.name = name;
        this.body = body;
        this.returnType = returnType;
    }
    public FuncDef(position pos, String name, Type returnType) {
        super(pos);
        this.name = name;
        this.returnType = returnType;
    }
    public FuncDef(position pos, String name, Type returnType, Type[] parameterTypes, String[] parameterIdentifiers) {
        super(pos);
        this.name = name;
        this.returnType = returnType;
        this.parameterTypes = new ArrayList<>(Arrays.asList(parameterTypes));
        this.parameterIdentifiers = new ArrayList<>(Arrays.asList(parameterIdentifiers));
    }


    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
