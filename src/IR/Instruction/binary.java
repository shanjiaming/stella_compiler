package IR.Instruction;

import AST.ASTVisitor;
import Backend.Pass;
import IR.Constant;
import IR.Entity;
import IR.Register;
import IR.Statement;

public class binary extends Statement {

    public Register lhs;
    public Entity op1, op2;
    public String op;
    public binary(Register lhs, Entity op1, Entity op2, String op) {
        super();
        this.lhs = lhs;
        this.op1 = op1;
        this.op2 = op2;
        this.op = op;
//        if (this.op1 instanceof Constant) {
//            this.op1 = op2;
//            this.op2 = op1;
//            if (this.op1 instanceof Constant) {
//                int i1 = ((Constant) op1).val, i2 = ((Constant) op2).val, i = 0;
//                if ("+".equals(op)) i = i1 + i2;
//                else if ("-".equals(op)) i = i1 - i2;
//                else if ("*".equals(op)) i = i1 * i2;
//                else if ("/".equals(op)) i = i1 / i2;
//                else if ("%".equals(op)) i = i1 % i2;
//                else if ("<<".equals(op)) i = i1 << i2;
//                else if (">>".equals(op)) i = i1 >> i2;
//                else if ("&".equals(op)) i = i1 & i2;
//                else if ("^".equals(op)) i = i1 ^ i2;
//                else if ("|".equals(op)) i = i1 | i2;
//                else if ("&&".equals(op)) i = i1 & i2;//hack bool type //maybe not right because of short path calculate
//                else if ("||".equals(op)) i = i1 | i2;//hack bool type //maybe not right because of short path calculate
//                else if ("==".equals(op)) i = (i1 == i2) ? 1 : 0;//hack bool type
//                else if ("!=".equals(op)) i = (i1 != i2) ? 1 : 0;//hack bool type
//                else if ("<".equals(op)) i = (i1 < i2) ? 1 : 0;//hack bool type
//                else if (">".equals(op)) i = (i1 > i2) ? 1 : 0;//hack bool type
//                else if ("<=".equals(op)) i = (i1 <= i2) ? 1 : 0;//hack bool type
//                else if (">=".equals(op)) i = (i1 >= i2) ? 1 : 0;//hack bool type
//                else assert(false);
//                this.op2 = new Constant(i);
//                this.op1 = new Constant(0);
//            }
//        }
        // Now, op1 is either register or zero
    }

    @Override public String toString() {return lhs + " = " + op1 + op + op2;}

    @Override
    public void accept(Pass visitor) {visitor.visit(this);}
}
