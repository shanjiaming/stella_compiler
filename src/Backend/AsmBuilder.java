package Backend;

import Asm.*;
import Asm.Instruction.*;
import IR.*;
import IR.Instruction.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static IR.Register.s0;

public class AsmBuilder extends Pass {


    //    今天我把这个改掉，并且出bug了。但是只要把它ASMBuilder还原回去，就没问题。因为网络问题，上个版本没push上去，那个版本里的就是正确答案。请先用oj检验正确答案再说。
//    开局和结束的move由于我的奇怪写法，无法抹掉。但是因为一个正常语句就64倍，应该没问题……吧？
//    还需要把IR里面的函数调用规范改成正常的，不要一股脑所有参数都放栈上了。
//    如何让move瞄准消除move染呢？
    public AsmEntry asmEntry;
    public AsmFunction asmFunction;
    public AsmBasicBlock asmBasicBlock;

    private Map<Integer, Integer> colormap;

    public AsmBuilder(IREntry irEntry, AsmEntry asmEntry) {
        super(irEntry);
        this.asmEntry = asmEntry;
        for (var i : irEntry.functions) {
            visit(i);
        }
        asmEntry.stringpool = irEntry.stringpool;
        asmEntry.globalpool = irEntry.globalpool;
    }

    private Reg RegisterToReg(Register register) {
        if (register == Register.sp) return Reg.sp;
        if (register == s0) return Reg.s0;
        if (register == Register.ra) return Reg.ra;
        if (register == Register.a0) return Reg.a0;
        if (register == Register.a1) return Reg.a1;
        if (register == Register.a2) return Reg.a2;
        if (register == Register.a3) return Reg.a3;
        if (register == Register.a4) return Reg.a4;
        if (register == Register.a5) return Reg.a5;
        if (register == Register.a6) return Reg.a6;
        if (register == Register.a7) return Reg.a7;
        if (register == Register.zero) return Reg.zero;
        if (register == Register.index) return Reg.index;
        for (int i = 0; i < 11; ++i) {
            if (register == Register.ss[i]) return Reg.ss[i];
        }
        return null;
    }

    boolean smallEnough(int x) {
        return -2048 <= x && x < 2048;
    }

    private Addr pointerRegisterToAddr(PointerRegister pointerRegister) {
        if (pointerRegister.isGlobal) return new Addr(pointerRegister.val);
        if (smallEnough(pointerRegister.address))
            return new Addr(pointerRegister.address, RegisterToReg(pointerRegister.offset));
        else {
            asmBasicBlock.push_back(new li(Reg.t5, pointerRegister.address));
            asmBasicBlock.push_back(new asmbinary(Reg.t5, Reg.t5, RegisterToReg(pointerRegister.offset), "+"));
            return new Addr(0, Reg.t5);
        }
    }

    private boolean colored(PointerRegister pointerRegister) {
        return pointerRegister.offset == Register.s0 && colormap.containsKey(pointerRegister.address);
    }

    private Reg lrp(Reg reg, PointerRegister pointerRegister) {
        Reg ret = reg;
        if (colored(pointerRegister)) {
            ret = Reg.ss[colormap.get(pointerRegister.address)];
            if (reg != ret) asmBasicBlock.push_back(new mv(ret, reg));
        } else {
            asmBasicBlock.push_back(new lw(reg, pointerRegisterToAddr(pointerRegister)));
        }
        return ret;
    }

    private Reg promise(Reg reg, PointerRegister pointerRegister) {
        Reg ret = reg;
        if (colored(pointerRegister)) {
            ret = Reg.ss[colormap.get(pointerRegister.address)];
        }
        return ret;
    }

    private void srp(Reg reg, PointerRegister pointerRegister) {
        if (colored(pointerRegister)) {
            Reg ret = Reg.ss[colormap.get(pointerRegister.address)];
            if (reg != ret) asmBasicBlock.push_back(new mv(reg, ret));
        } else {
            asmBasicBlock.push_back(new sw(reg, pointerRegisterToAddr(pointerRegister)));
        }
    }


    @Override
    public void visit(Function it) {
        colormap = it.colormap;
        asmFunction = new AsmFunction();
        for (var b : it.basicBlocks) {
            visit(b);
            asmFunction.asmBasicBlocks.add(asmBasicBlock);
            if (asmFunction.fnAsmBasicBlock == null) asmFunction.fnAsmBasicBlock = asmBasicBlock;
        }
        asmEntry.functions.add(asmFunction);
    }

    @Override
    public void visit(BasicBlock basicBlock) {
        asmBasicBlock = new AsmBasicBlock(basicBlock.name);
        for (var s : basicBlock.stmts) {
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
        Reg op1 = lrp(Reg.op1, it.op1);
        Reg op2 = lrp(Reg.op2, it.op2);
        Reg dest = promise(Reg.dest, it.lhs);
        asmBasicBlock.push_back(new asmbinary(dest, op1, op2, it.op));
        srp(dest, it.lhs);

//        }
    }

    public void visit(addri it) {
        if (smallEnough(it.op2))
            asmBasicBlock.push_back(new asmbinaryi(RegisterToReg(it.lhs), RegisterToReg(it.op1), it.op2, "+"));
        else {
            asmBasicBlock.push_back(new li(Reg.op2, it.op2));
            asmBasicBlock.push_back(new asmbinary(RegisterToReg(it.lhs), RegisterToReg(it.op1), Reg.op2, "+"));
        }
    }

    public void visit(binaryi it) {
        Reg op1 = lrp(Reg.op1, it.op1);
        Reg dest = promise(Reg.dest, it.lhs);
        if (smallEnough(it.op2)) {
            asmBasicBlock.push_back(new asmbinaryi(dest, op1, it.op2, it.op));
        } else {
            asmBasicBlock.push_back(new li(Reg.op2, it.op2));
            asmBasicBlock.push_back(new asmbinary(dest, op1, Reg.op2, it.op));
        }
        srp(dest, it.lhs);
    }


    public void visit(branch it) {
        Reg op1 = lrp(Reg.op1, it.op);
        asmBasicBlock.push_back(new beqz(op1, it.falseBranch.name));
        asmBasicBlock.push_back(new j(it.trueBranch.name));
    }


    public void visit(callfunc it) {
        asmBasicBlock.push_back(new call(it.callFuncName));
    }

    public void visit(jump it) {
        asmBasicBlock.push_back(new j(it.destination.name));
    }

    public void visit(loadinst it) {
        Reg dest = promise(Reg.dest, it.pointer);
        asmBasicBlock.push_back(new li(dest, it.constant));
        srp(dest, it.pointer);
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
        srp(Reg.a0, it.pointer);
    }

    public void visit(move it) {
        Reg prom = promise(Reg.op1, it.pdest);
        Reg op1 = lrp(prom, it.psrc);
        srp(op1, it.pdest);
    }

    public void visit(reter it) {
        asmBasicBlock.push_back(new ret());
    }

    public void visit(store it) {
        srp(RegisterToReg(it.reg), it.pointer);
    }

    @Override
    public void visit(blockfirst it) {

    }
}
