package AST;

import Util.position;

import java.util.ArrayList;

public class MemberFuncCallExpr extends Expr{
    public FuncCallExpr proxyFuncCall;

    public Expr lhs;
    public String name;
    public ArrayList<Expr> argList = new ArrayList<>();


    public MemberFuncCallExpr(Expr lhs, FuncCallExpr rhs) {
        super(false);
        this.lhs = lhs;
        this.name = rhs.name;
        this.argList = rhs.argList;
    }

    public MemberFuncCallExpr(position pos, Expr lhs, FuncCallExpr rhs) {
        super(pos,false);
        this.lhs = lhs;
        this.name = rhs.name;
        this.argList = rhs.argList;
    }

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
