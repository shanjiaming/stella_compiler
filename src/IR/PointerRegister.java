package IR;

public class PointerRegister extends Register {
    public Constant address = new Constant();

    public PointerRegister() {
        super();
    }

    @Override public String toString() {return "mem[" + address + "]";}


}
