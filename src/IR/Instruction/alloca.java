package IR.Instruction;

import IR.*;
import Util.Type;

import java.util.Stack;

public class alloca extends Statement {

    public PointerRegister pointerRegister;

    public int length;

    private static final Stack<Integer> previous_sp = new Stack<>();
    private static int sp = 0;

    public static void remebersp(){
        previous_sp.add(sp);
    }

    public static void resetsp(){
        sp = previous_sp.pop();
    }

    public alloca(PointerRegister pointerRegister, int length) {
        this.pointerRegister = pointerRegister;
        pointerRegister.address = new Constant(sp);
        sp += length;
        this.length = length;
    }

    @Override public String toString() {return "//ir_alloca(" + pointerRegister + ", " + length + ")";}
}
