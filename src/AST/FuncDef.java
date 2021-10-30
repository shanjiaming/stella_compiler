package AST;

import Util.position;
import Util.Type;

import java.util.ArrayList;

public class FuncDef extends ASTNode implements ProgramUnit{
    public Type returnType;
    public ArrayList<Type> parameterTypes = new ArrayList<>();
    public ArrayList<String> parameterIdentifiers = new ArrayList<>();
    public SuiteStmt body;
    public String name;
//    public function func;

    public FuncDef(position pos, String name, SuiteStmt body, Type returnType) {
        super(pos);
        this.name = name;
        this.body = body;
        this.returnType = returnType;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
