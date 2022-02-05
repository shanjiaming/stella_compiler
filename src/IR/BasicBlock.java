package IR;

import Backend.AsmBuilder;
import IR.Instruction.blockfirst;
import IR.Instruction.branch;
import IR.Instruction.jump;

import java.util.ArrayList;

public class BasicBlock {


    public ArrayList<Statement> stmts = new ArrayList<>();
    public terminalStmt tailStmt = null;
    public blockfirst blockfirststmt = new blockfirst();

    private static int counter = 0;
    public String name;

    public BasicBlock() {
        name = "block_" + (counter++);
    }

    public BasicBlock(String name) {
        this.name = name + "_block_" + (counter++);
    }

    public void push_back(Statement stmt) {
        if (stmt instanceof terminalStmt) {
            assert (tailStmt == null);
            tailStmt = (terminalStmt) stmt;
            if (stmt instanceof jump) {
                Statement next = ((jump) stmt).destination.blockfirststmt;
                stmt.goout.add(next);
                next.goin.add(stmt);
            } else if (stmt instanceof branch) {
                Statement next = ((branch) stmt).trueBranch.blockfirststmt;
                stmt.goout.add(next);
                next.goin.add(stmt);
                next = ((branch) stmt).falseBranch.blockfirststmt;
                stmt.goout.add(next);
                next.goin.add(stmt);
            }
        }
        Statement last = stmts.isEmpty() ? blockfirststmt : stmts.get(stmts.size() - 1);
        last.goout.add(stmt);
        stmt.goin.add(last);
        stmts.add(stmt);
    }
}
