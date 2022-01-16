package IR.Instruction;

import AST.Expr;
import Backend.Pass;
import IR.Statement;
import IR.terminalStmt;

import java.util.ArrayList;
import java.util.StringJoiner;

public class callfunc extends Statement {
    public String callFuncName;
    public callfunc(String name){callFuncName = name;}

    @Override public String toString() {
        return callFuncName + "(" + ")";
    }

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}
}
