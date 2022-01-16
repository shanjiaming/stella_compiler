package Asm;

public class Imm {
    public static boolean isImm(int x){
        return x < (1<<11) && x >= -(1<<11);
    }

    public static int getlowImm(int x){
        int x0 = x & ((1 << 12) - 1);
        return x < (1<<11) ? x : x - (1 << 12) ;
    }
    public static int gethighImm(int x){
        return (x >> 12) + ((x >> 11) & 1);
    }


}
