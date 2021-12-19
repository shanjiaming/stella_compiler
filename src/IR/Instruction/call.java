package IR.Instruction;

import AST.Expr;
import IR.Statement;
import IR.terminalStmt;

import java.util.ArrayList;
import java.util.StringJoiner;

public class call extends terminalStmt {
    public String callFuncName;
    public ArrayList<Expr> argList = new ArrayList<>();
    public call(String name){callFuncName = name;}
    public call(String name, ArrayList<Expr> argList){
        callFuncName = name;
        this.argList = argList;
    }

    @Override public String toString() {
        StringJoiner sj = new StringJoiner(", ", callFuncName + "(", ")");
        for(Expr expr : argList){
            sj.add(expr.entity.toString());
        }
        return sj.toString();
    }
}
