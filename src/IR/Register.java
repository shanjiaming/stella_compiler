package IR;

public class Register extends Entity {

    private static int counter;

    public Register() {
        val = "" +(counter++);
    }

    @Override public String toString() {return "reg[" + val + "]";}
}
