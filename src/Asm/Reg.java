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
    public static Reg dest = new Reg("dest");
    public static Reg op1 = new Reg("op1");
    public static Reg op2 = new Reg("op2");
    public static Reg index = new Reg("index");
    public static Reg zero = new Reg("zero");


    public Reg(String name){
        this.name = name;
    }
}
