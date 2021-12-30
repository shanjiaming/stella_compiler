package IR.Instruction;

import AST.Expr;
import IR.Statement;
import IR.terminalStmt;

import java.util.ArrayList;
import java.util.StringJoiner;

public class call extends terminalStmt {
    public String callFuncName;
    public call(String name){callFuncName = name;}

    @Override public String toString() {
        return callFuncName + "(" + ")";
    }
}
