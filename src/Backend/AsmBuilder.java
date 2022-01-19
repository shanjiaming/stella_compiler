package Backend;

import Asm.*;
import Asm.Instruction.*;
import IR.*;
import IR.Instruction.*;

import java.util.ArrayList;

public class AsmBuilder extends Pass {

    public AsmEntry asmEntry;
    public AsmFunction asmFunction;
    public AsmBasicBlock asmBasicBlock;


    public AsmBuilder(IREntry irEntry, AsmEntry asmEntry) {
        super(irEntry);
        this.asmEntry = asmEntry;
        for(var i : irEntry.functions){
            visit(i);
        }
        asmEntry.stringpool = irEntry.stringpool;
        asmEntry.globalpool = irEntry.globalpool;
    }

    private Reg RegisterToReg(Register register){
        if(register == Register.sp) return Reg.sp;
        if(register == Register.s0) return Reg.s0;
        if(register == Register.ra) return Reg.ra;
        if(register == Register.a0) return Reg.a0;
        if(register == Register.a1) return Reg.a1;
        if(register == Register.a2) return Reg.a2;
        if(register == Register.a3) return Reg.a3;
        if(register == Register.a4) return Reg.a4;
        if(register == Register.a5) return Reg.a5;
        if(register == Register.a6) return Reg.a6;
        if(register == Register.a7) return Reg.a7;
        if(register == Register.zero) return Reg.zero;
        if(register == Register.index) return Reg.index;
        return null;
    }

    private Addr pointerRegisterToAddr(PointerRegister pointerRegister){
        if(pointerRegister.isGlobal) return new Addr(pointerRegister.val);
        if((pointerRegister.address >> 12) == 0)
        return new Addr(pointerRegister.address, RegisterToReg(pointerRegister.offset));
        else {
            asmBasicBlock.push_back(new li(Reg.t5, pointerRegister.address));
            asmBasicBlock.push_back(new asmbinary(Reg.t5, Reg.t5, RegisterToReg(pointerRegister.offset), "+"));
            return new Addr(0, Reg.t5);
        }
    }

    private void lrp(Reg reg, PointerRegister pointerRegister){
        asmBasicBlock.push_back(new lw(reg, pointerRegisterToAddr(pointerRegister)));
    }

    private void srp(Reg reg, PointerRegister pointerRegister){
        asmBasicBlock.push_back(new sw(reg, pointerRegisterToAddr(pointerRegister)));
    }


    @Override
    public void visit(Statement it) {
        if(it instanceof addri) visit((addri) it);
        else if(it instanceof binary) visit((binary) it);
        else if(it instanceof binaryi) visit((binaryi) it);
        else if(it instanceof branch) visit((branch) it);
        else if(it instanceof callfunc) visit((callfunc) it);
        else if(it instanceof jump) visit((jump) it);
        else if(it instanceof load) visit((load) it);
        else if(it instanceof loadinst) visit((loadinst) it);
        else if(it instanceof loadrinst) visit((loadrinst) it);
        else if(it instanceof malloci) visit((malloci) it);
        else if(it instanceof move) visit((move) it);
        else if(it instanceof reter) visit((reter) it);
        else if(it instanceof store) visit((store) it);
        else assert (false);
    }

    @Override
    public void visit(Function it) {
        asmFunction = new AsmFunction();
        for( var b : it.basicBlocks){
            visit(b);
            asmFunction.asmBasicBlocks.add(asmBasicBlock);
            if(asmFunction.fnAsmBasicBlock == null) asmFunction.fnAsmBasicBlock = asmBasicBlock;
        }
        asmEntry.functions.add(asmFunction);
    }

    @Override
    public void visit(BasicBlock basicBlock) {
        asmBasicBlock = new AsmBasicBlock(basicBlock.name);
        for(var s: basicBlock.stmts()){
            s.accept(this);
        }
    }

    public void visit(binary it) {


//        if ("<=".equals(it.op)) {
//            var lhs = washdest(it.lhs);
//            asmBasicBlock.push_back(new asmbinary(lhs, entityToReg(it.op2), entityToReg(it.op1), "<"));
//            asmBasicBlock.push_back(new asmbinaryi(lhs, lhs, 1, "^"));
//        } else if (">".equals(it.op)) {
//            asmBasicBlock.push_back(new asmbinary(washdest(it.lhs), entityToReg(it.op2), entityToReg(it.op1), "<"));
//        } else if (">=".equals(it.op)) {
//            var lhs = washdest(it.lhs);
//            asmBasicBlock.push_back(new asmbinary(lhs, entityToReg(it.op1), entityToReg(it.op2), "<"));
//            asmBasicBlock.push_back(new asmbinaryi(lhs, lhs, 1, "^"));
//        } else {
        lrp(Reg.op1, it.op1);
        lrp(Reg.op2, it.op2);
        asmBasicBlock.push_back(new asmbinary(Reg.dest, Reg.op1, Reg.op2, it.op));
        srp(Reg.dest, it.lhs);

//        }
    }

    public void visit(addri it) {
        asmBasicBlock.push_back(new asmbinaryi(RegisterToReg(it.lhs), RegisterToReg(it.op1), it.op2, "+"));
    }

    public void visit(binaryi it) {
        lrp(Reg.op1, it.op1);
        asmBasicBlock.push_back(new li(Reg.op2, it.op2));
        asmBasicBlock.push_back(new asmbinary(Reg.dest, Reg.op1, Reg.op2, it.op));
        srp(Reg.dest, it.lhs);
    }


    public void visit(branch it) {
        lrp(Reg.op1, it.op);
        asmBasicBlock.push_back(new beqz(Reg.op1, it.falseBranch.name));
        asmBasicBlock.push_back(new j(it.trueBranch.name));
    }


    public void visit(callfunc it) {
        asmBasicBlock.push_back(new call(it.callFuncName));
    }

    public void visit(jump it) {
        asmBasicBlock.push_back(new j(it.destination.name));
    }

    public void visit(loadinst it) {
        asmBasicBlock.push_back(new li(Reg.dest, it.constant));
        srp(Reg.dest, it.reg);
    }
    public void visit(loadrinst it) {
        asmBasicBlock.push_back(new li(RegisterToReg(it.reg), it.constant));
    }

    public void visit(load it) {
        lrp(RegisterToReg(it.reg), it.pointer);
    }

    public void visit(malloci it) {
        asmBasicBlock.push_back(new li(Reg.a0, it.length));
        asmBasicBlock.push_back(new call("malloc"));
        srp(Reg.a0, it.register);
    }

    public void visit(move it) {
        lrp(Reg.op1, it.psrc);
        srp(Reg.op1, it.pdest);
    }

    public void visit(reter it) {
        asmBasicBlock.push_back(new ret());
    }

    public void visit(store it) {
        srp(RegisterToReg(it.reg), it.pointer);
    }
}
