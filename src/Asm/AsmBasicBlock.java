package Asm;

import java.util.ArrayList;

public class AsmBasicBlock {



    private ArrayList<AsmStmt> stmts = new ArrayList<>();

    private static int counter = 0;
    public String name;
    public AsmBasicBlock() {
        name = "block_" + (counter++);
    }
    public AsmBasicBlock(String name) {
        this.name = name + "_block_" + (counter++);
    }

    public void push_back(AsmStmt stmt) {
        stmts.add(stmt);
    }

    public ArrayList<AsmStmt> stmts() {
        return new ArrayList<>(stmts);
    }


}
