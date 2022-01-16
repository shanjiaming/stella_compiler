package Asm;

public class Addr {
    public int inst;
    public Reg reg;

    public Addr(int inst, Reg reg){
        this.inst = inst;
        this.reg = reg;
    }

    @Override public String toString() {
        return inst + "(" + reg + ")";
    }
}
