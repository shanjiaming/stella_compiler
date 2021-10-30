package AST;

import Util.position;

import java.util.ArrayList;

public class ClassDef extends ASTNode implements ProgramUnit{
    public String name;
    public ArrayList<ConstructDef> constructDefs = new ArrayList<>();
    public ArrayList<FuncDef> funcDefs = new ArrayList<>();
    public ArrayList<VarDefStmt> varDefStmts = new ArrayList<>();
    public ClassDef(position pos, String name) {
        super(pos);
        this.name = name;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
