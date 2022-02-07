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
    public static Register a1 = new Register("a1");
    public static Register a2 = new Register("a2");
    public static Register a3 = new Register("a3");
    public static Register a4 = new Register("a4");
    public static Register a5 = new Register("a5");
    public static Register a6 = new Register("a6");
    public static Register a7 = new Register("a7");
    public static Register zero = new Register("zero");
    public static Register index = new Register("index");
    public static Register[] ss = new Register[]{
            new Register("gp"),
            new Register("tp"),
            new Register("s1"),
            new Register("s2"),
            new Register("s3"),
            new Register("s4"),
            new Register("s5"),
            new Register("s6"),
            new Register("s7"),
            new Register("s8"),
            new Register("s9"),
    };


    public static final int ssSIZE = ss.length;

    public static Register[] a = new Register[] {a0,a1,a2,a3,a4,a5,a6,a7};

    @Override public String toString() {return "reg[" + val + "]";}

    public String regToString() {return "reg[" + val + "]";}
}
