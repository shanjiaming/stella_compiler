package IR;

import IR.Instruction.branch;
import IR.Instruction.jump;

import java.util.ArrayList;

public class BasicBlock {



    private ArrayList<Statement> stmts = new ArrayList<>();
    private terminalStmt tailStmt = null;

    private static int counter = 0;
    public String name;
    public BasicBlock() {
        name = "block_" + (counter++);
    }
    public BasicBlock(String name) {
        this.name = name + "_block_" + (counter++);
    }

    public void push_back(Statement stmt) {
        stmts.add(stmt);
//        if(stmt instanceof terminalStmt){
//            assert (tailStmt == null);
//            tailStmt = (terminalStmt) stmt;
//        }
    }

    public ArrayList<Statement> stmts() {
        return new ArrayList<>(stmts);
    }

}
