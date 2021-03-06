package IR.Instruction;

import AST.Expr;
import Backend.Pass;
import IR.PointerRegister;
import IR.Statement;
import IR.terminalStmt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class callfunc extends Statement {
    public String callFuncName;
    public callfunc(String name){callFuncName = name;}

    @Override public String toString() {
        return callFuncName + "(" + ")";
    }

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}

    @Override
    public Set<Integer> defs() {
        Set<Integer> ret = new HashSet<>();
        //TODO add 所有有可能会之后改变的寄存器
        return ret;
    }

    public Set<Integer> uses() {
        Set<Integer> ret = new HashSet<>();
        return ret;
    }
}
