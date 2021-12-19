package AST;

import Util.MxError.SemanticError;
import Util.Type;
import Util.position;

import java.util.ArrayList;
import java.util.Arrays;

public class BinaryExpr extends Expr {
    public Expr lhs, rhs;
    public String op;


    public BinaryExpr(Expr lhs, Expr rhs, String op) {
        super(false);
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
    }


    public BinaryExpr(position pos, Expr lhs, Expr rhs, String op) {
        super(pos, false);
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
