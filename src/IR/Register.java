package IR;

public class Register extends Entity {

    private static int counter;

    public Register() {
        val = "" +(counter++);
    }

    public Register(String name) {
        val = "/*" + name + "*/" +(counter++);
    }

    public static Register sp = new Register("sp");
    public static Register s0 = new Register("s0");
    public static Register ra = new Register("ra");
    public static Register a0 = new Register("a0");
    public static Register zero = new Register("zero");

    @Override public String toString() {return "reg[" + val + "]";}

    public String regToString() {return "reg[" + val + "]";}
}
