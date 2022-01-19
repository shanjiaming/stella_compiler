package Asm.Instruction;

import Asm.AsmStmt;
import Asm.Reg;
import IR.Entity;
import IR.Register;

public class asmbinary extends AsmStmt {

    public Reg lhs;
    public Reg op1, op2;
    public String op;
    private String signop;

    public asmbinary(Reg lhs, Reg op1, Reg op2, String op) {
        this.lhs = lhs;
        this.op1 = op1;
        this.op2 = op2;
        signop = op;

        switch (op){
            case "+" -> this.op = "add";
            case "-" -> this.op = "sub";
            case "*" -> this.op = "mul";
            case "/" -> this.op = "div";
            case "%" -> this.op = "rem";

            case "<<" -> this.op = "sll";
            case ">>" -> this.op = "sra";

            case "==" -> this.op = "xor";
            case "!=" -> this.op = "xor";

            case "<" -> this.op = "slt";
            case ">=" -> this.op = "slt";
            case ">" -> this.op = "sgt";
            case "<=" -> this.op = "sgt";

            case "|" -> this.op = "or";
            case "^" -> this.op = "xor";
            case "&" -> this.op = "and";
            default -> {
                System.out.println(op);
                assert false;
            }
        }
    }


    @Override public String toString() {
        String s = op + " " + lhs + ", " + op1 + ", " + op2;
        if("<=".equals(signop) || ">=".equals(signop) )
        s += ("\n\txori "+lhs+", "+lhs+", 1");
        if("==".equals(signop))
        s += ("\n\tseqz "+lhs+", " + lhs);
        if("!=".equals(signop))
        s += ("\n\tsnez "+lhs+", " + lhs);
        return  s;
    }

}
