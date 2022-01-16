package Asm.Instruction;

import Asm.AsmStmt;

public class call extends AsmStmt {
    public String callFuncName;
    public call(String name){callFuncName = name;}

    @Override public String toString() {
        return "call " + callFuncName;
    }
}
