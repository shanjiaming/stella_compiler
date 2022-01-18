package Asm.Instruction;

import Asm.AsmStmt;
import Asm.Reg;
import IR.Entity;
import IR.Register;

public class asmbinary extends AsmStmt {

    public Reg lhs;
    public Reg op1, op2;
    public String op;
    public asmbinary(Reg lhs, Reg op1, Reg op2, String op) {
        this.lhs = lhs;
        if("<=".equals(op) || ">=".equals(op)){
            this.op1 = op2;
            this.op2 = op1;
        }else {
            this.op1 = op1;
            this.op2 = op2;
        }
        switch (op){
            case "+" -> this.op = "add";
            case "-" -> this.op = "sub";
            case "*" -> this.op = "mul";
            case "/" -> this.op = "div";
            case "%" -> this.op = "rem";

            case "<<" -> this.op = "sll";
            case ">>" -> this.op = "sra";

            case "<" -> this.op = "slt";
            case ">" -> this.op = "sgt";

            case "|" -> this.op = "or";
            case "^" -> this.op = "xor";
            case "&" -> this.op = "and";




        }
    }


    @Override public String toString() {return op + " " + lhs + ", " + op1 + ", " + op2;}

}
