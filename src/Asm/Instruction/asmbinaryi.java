package Asm.Instruction;

import Asm.AsmStmt;
import Asm.Reg;

public class asmbinaryi extends AsmStmt {

    public Reg lhs;
    public Reg op1;
    public int op2;
    public String op;
    public asmbinaryi(Reg lhs, Reg op1, int op2, String op) {
        this.lhs = lhs;
        this.op1 = op1;
        this.op2 = op2;
        switch (op){
            case "+" -> this.op = "addi";
            case "^" -> this.op = "xori";
        }
    }


    @Override public String toString() {return op + " " + lhs + " " + op1 + " " + op2;}

}
