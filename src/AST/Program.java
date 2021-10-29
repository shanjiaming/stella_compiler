package AST;

import Util.position;

import java.util.ArrayList;

public class Program extends ASTNode{

    public ArrayList<ClassDef> classDefs = new ArrayList<>();
    public ArrayList<FuncDef> funcDefs = new ArrayList<>();
    public ArrayList<VarDefStmt> varDefStmts = new ArrayList<>();

    public Program(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}