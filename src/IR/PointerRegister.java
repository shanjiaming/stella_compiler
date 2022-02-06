package IR;

public class PointerRegister extends Entity {


    public int address  = 0;//todo 必须是相对sp，虽然这个const不会变
    public Register offset = Register.s0;
    public PointerRegister() {
        super();
    }

    public boolean isGlobal = false;

    public PointerRegister(String name) {
        super();
        val += "/*" + name + "*/";
    }
    public PointerRegister(String name, boolean isGlobal) {
        super();
        val = name;
        this.isGlobal = isGlobal;
        if(isGlobal) offset = Register.zero;
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

    @Override
    public String toString() {return (val != null && val.length() >= 4 && "str_".equals(val.substring(0,4))) ? val.substring(4) : isGlobal ? val : "mem[" + address + "+" + offset.regToString() + "]";}

//    public int intAddress(){
//        return Integer.parseInt(address.val);
//    }



}
