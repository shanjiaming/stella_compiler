package Asm.Instruction;

import Asm.AsmStmt;
import Asm.Reg;

public class asmbinaryi extends AsmStmt {

    public Reg lhs;
    public Reg op1;
    public int op2;
    public String op;
    private String signop;

    public asmbinaryi(Reg lhs, Reg op1, int op2, String op) {
        this.lhs = lhs;
        this.op1 = op1;
        this.op2 = ("-".equals(op))? -op2 :op2;
        signop = op;

        switch (op){
            case "+" -> this.op = "addi";
            case "-" -> this.op = "addi";
            case "*" -> this.op = "muli";
            case "^" -> this.op = "xori";

            case "/" -> this.op = "divi";
            case "%" -> this.op = "remi";

            case "<<" -> this.op = "slli";
            case ">>" -> this.op = "srai";

            case "==" -> this.op = "xori";
            case "!=" -> this.op = "xori";

            case "<" -> this.op = "slti";
            case ">=" -> this.op = "slti";
            case ">" -> this.op = "sgti";
            case "<=" -> this.op = "sgti";

            case "|" -> this.op = "ori";
            case "&" -> this.op = "andi";
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
