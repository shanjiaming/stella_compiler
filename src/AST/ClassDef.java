package AST;

import Util.Type;
import Util.position;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassDef extends ASTNode implements ProgramUnit{
    public String name;
    public ConstructDef constructDef;

    public HashMap<String,FuncDef> funcDefs = new HashMap<>();
    public HashMap<String,Type> members = new HashMap<>();
    public ClassDef(position pos, String name) {
        super(pos);
        this.name = name;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
