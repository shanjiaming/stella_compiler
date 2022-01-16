package Backend;

import Asm.*;
import Asm.Instruction.*;
import IR.*;
import IR.Instruction.*;

public class AsmBuilder extends Pass {

    public AsmEntry asmEntry;
    public AsmFunction asmFunction;
    public AsmBasicBlock asmBasicBlock;

    public AsmBuilder(IREntry irEntry, AsmEntry asmEntry) {
        super(irEntry);
        this.asmEntry = asmEntry;
    }

    private Reg washdest(Register reg) {
        return new Reg();
    }

    private Reg washread(Register reg) {
        return new Reg();
    }

    private Reg entityToReg(Entity entity) {
        if(entity instanceof Constant){

        }else if(entity instanceof PointerRegister){

        }else{

        }
        return new Reg();
    }

    private AsmBasicBlock BasicBlockToAsm(BasicBlock basicBlock){
        return new AsmBasicBlock(basicBlock.name);
    }



    @Override
    public void visit(Statement it) {
        it.accept(this);
    }

    @Override
    public void visit(Function it) {
//        asmFunction = new AsmFunction(it.);
//        it.accept(this);
    }

    public void visit(binary it) {

        if ("<=".equals(it.op)) {
            var lhs = washdest(it.lhs);
            asmBasicBlock.push_back(new asmbinary(lhs, entityToReg(it.op2), entityToReg(it.op1), "<"));
            asmBasicBlock.push_back(new asmbinaryi(lhs, lhs, 1, "^"));
        } else if (">".equals(it.op)) {
            asmBasicBlock.push_back(new asmbinary(washdest(it.lhs), entityToReg(it.op2), entityToReg(it.op1), "<"));
        } else if (">=".equals(it.op)) {
            var lhs = washdest(it.lhs);
            asmBasicBlock.push_back(new asmbinary(lhs, entityToReg(it.op1), entityToReg(it.op2), "<"));
            asmBasicBlock.push_back(new asmbinaryi(lhs, lhs, 1, "^"));
        } else {
            asmBasicBlock.push_back(new asmbinary(washdest(it.lhs), entityToReg(it.op1), entityToReg(it.op2), it.op));
        }
    }

    public void visit(branch it) {
        asmBasicBlock.push_back(new bnez(entityToReg(it.op), BasicBlockToAsm(it.trueBranch)));
        asmBasicBlock.push_back(new beqz(entityToReg(it.op), BasicBlockToAsm(it.falseBranch)));
    }

    public void visit(callfunc it) {
        asmBasicBlock.push_back(new call(it.callFuncName));
    }

    public void visit(jump it) {
        asmBasicBlock.push_back(new j(it.destination));
    }

    public void visit(loadinst it) {
        int v = Integer.parseInt(it.constant.val);
        int l = v & (0b111111111111);
        int r = v >> 12;
        Reg reg = washdest(it.reg);
        asmBasicBlock.push_back(new li(reg, l));
        if (r != 0)
            asmBasicBlock.push_back(new lui(reg, r));
    }

    public void visit(load it) {
        asmBasicBlock.push_back(new lw(washdest(it.reg), new Addr(it.pointer.address, washread(it.pointer))));
    }

    public void visit(malloc it) {
        new move(Register.a0, it.length).accept(this);
        asmBasicBlock.push_back(new call("malloc"));
        asmBasicBlock.push_back(new mv(washdest(it.register), Reg.a0));
    }

    public void visit(move it) {
        if (it.entity instanceof Constant) new loadinst(it.reg, (Constant) it.entity).accept(this);
        else if (it.entity instanceof PointerRegister) new load((PointerRegister) it.entity, it.reg).accept(this);
        else asmBasicBlock.push_back(new mv(washdest(it.reg), washread((Register) it.entity)));
    }

    public void visit(reter it) {
        asmBasicBlock.push_back(new ret());
    }

    public void visit(store it) {
        asmBasicBlock.push_back(new sw(entityToReg(it.entity), new Addr(it.pointer.address, washread(it.pointer))));
    }
}
