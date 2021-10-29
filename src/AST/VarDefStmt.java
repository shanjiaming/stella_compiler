package AST;

import Util.position;

import java.util.ArrayList;

public class VarDefStmt extends Stmt {
    public Type varType;
    public ArrayList<String> names = new ArrayList<>();
    public ArrayList<Expr> init = new ArrayList<>();

    public VarDefStmt(position pos, Type varType) {
        super(pos);
        this.varType = varType;
    }

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}
}