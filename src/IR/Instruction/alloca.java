package IR.Instruction;

import IR.*;
import Util.Type;

import java.util.Stack;

public class alloca extends Statement {

    public PointerRegister pointerRegister;

    public int length;

    private static final Stack<Integer> previous_sp = new Stack<>();
    private static int sp = 0;

    public static void remebersp(){//这个东西只在函数那边才会变——吗？for循环怎么办？
        previous_sp.add(sp);
    }

    public static void resetsp(){//这个东西只在函数那边才会变
        sp = previous_sp.pop();
    }

    public alloca(PointerRegister pointerRegister, int length) {
        this.pointerRegister = pointerRegister;
        pointerRegister.address = sp;
        sp += length;
        this.length = length;
    }

    @Override public String toString() {return "//ir_alloca(" + pointerRegister + ", " + length + ")";}
}
