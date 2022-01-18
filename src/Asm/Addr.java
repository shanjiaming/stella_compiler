package Asm;

public class Addr {
    public int inst;
    public Reg reg;

    public String globalName;

    public Addr(int inst, Reg reg){
        this.inst = inst;
        this.reg = reg;
    }

    public Addr(String globalName){
        this.globalName = globalName;
    }

    @Override public String toString() {
        return (globalName != null) ? globalName : (inst + "(" + reg + ")");
    }
}
