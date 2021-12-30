package IR;

public class PointerRegister extends Register {
    public int address  = 0;//todo 必须是相对sp，虽然这个const不会变
    public Register offset = Register.s0;
    public PointerRegister() {
        super();
    }

    public PointerRegister(String name) {
        super();
        val += "/*" + name + "*/";
    }
    public PointerRegister(int val) {
        super();
        address = val;
    }

    public PointerRegister(int val, Register offset) {
        super();
        address = val;
        this.offset = offset;
    }

    static public PointerRegister min12;

    public String memToString() {return "mem[" + address + "+" + offset.regToString() + "]";}

//    public int intAddress(){
//        return Integer.parseInt(address.val);
//    }



}
