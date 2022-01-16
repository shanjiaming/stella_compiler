package AST;

import IR.PointerRegister;
import Util.position;
import Util.Type;

import java.util.ArrayList;

public class VarDefStmt extends Stmt implements ProgramUnit{
    public Type varType;
    public ArrayList<String> names = new ArrayList<>();
    public ArrayList<Expr> init = new ArrayList<>();
    public ArrayList<PointerRegister> vars = new ArrayList<>();
    public boolean isGlobal = false;

    public VarDefStmt(Type varType) {
        this.varType = varType;
    }

    public VarDefStmt(position pos, Type varType) {
        super(pos);
        this.varType = varType;
    }

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}


}