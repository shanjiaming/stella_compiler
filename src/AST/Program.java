package AST;

import Util.position;

import java.util.ArrayList;

public class Program extends ASTNode{

    public ArrayList<ProgramUnit> programUnits = new ArrayList<>();

    public Program() {
    }
    public Program(position pos) {
        super(pos);
    }

    public int globalframesize;

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }


}