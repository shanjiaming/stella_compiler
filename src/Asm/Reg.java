package Asm;

import IR.Register;

public class Reg {

    public String name;

    public static Reg sp = new Reg("sp");
    public static Reg s0 = new Reg("s0");
    public static Reg ra = new Reg("ra");
    public static Reg a0 = new Reg("a0");
    public static Reg a1 = new Reg("a1");
    public static Reg a2 = new Reg("a2");
    public static Reg a3 = new Reg("a3");
    public static Reg a4 = new Reg("a4");
    public static Reg a5 = new Reg("a5");
    public static Reg a6 = new Reg("a6");
    public static Reg a7 = new Reg("a7");
    public static Reg dest = new Reg("t0");
    public static Reg op1 = new Reg("t1");
    public static Reg op2 = new Reg("t2");
    public static Reg index = new Reg("t3");
    //t4 在sw global的时候也用到了
    public static Reg zero = new Reg("zero");


    public Reg(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
