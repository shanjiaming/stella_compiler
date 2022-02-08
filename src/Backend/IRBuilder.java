package Backend;

import AST.*;
import IR.*;
import IR.Instruction.*;
import Util.Type;

import java.util.ArrayList;
import java.util.Stack;


public class IRBuilder extends ASTVisitor {
    private Function fn;
    private BasicBlock currentBasicBlock;
    private BasicBlock returnBlock;
    private final IREntry irEntry;
    private Stack<BasicBlock> continueTo = new Stack<>();
    private Stack<BasicBlock> breakTo = new Stack<>();

    public IRBuilder(IREntry irEntry) {
        this.irEntry = irEntry;
    }

    private boolean isGlobal = false;

    public static int STACKSTARTSIZE = 12 + 4 * Register.ssSIZE;

    @Override
    public void visit(Program it) {
        ArrayList<VarDefStmt> globalVars = new ArrayList<>();
        ArrayList<ClassDef> classDefs = new ArrayList<>();
        ArrayList<FuncDef> funcDefs = new ArrayList<>();
        it.programUnits.forEach(programUnit -> {
            if (programUnit instanceof ClassDef)
                classDefs.add((ClassDef) programUnit);
//                ((ClassDef) programUnit).accept(this);
            if (programUnit instanceof FuncDef)
                funcDefs.add((FuncDef) programUnit);
//            ((FuncDef) programUnit).accept(this);
            if (programUnit instanceof VarDefStmt)
                globalVars.add((VarDefStmt) programUnit);
        });
        currentBasicBlock = new BasicBlock("global_init");
        fn = new Function("global_init", currentBasicBlock);
        irEntry.functions.add(fn);
        currentBasicBlock.push_back(new addri(Register.sp, Register.sp, -it.globalframesize));
        PointerRegister raPointer = new PointerRegister(it.globalframesize - 4, Register.sp);
        PointerRegister s0Pointer = new PointerRegister(it.globalframesize - 8, Register.sp);
        currentBasicBlock.push_back(new store(raPointer, Register.ra, true));//这两个寄存器的操作也可以化成s0版本的
        currentBasicBlock.push_back(new store(s0Pointer, Register.s0));
        for (int i = 0; i < Register.ssSIZE; ++i) {
            currentBasicBlock.push_back(new store(new PointerRegister(it.globalframesize - 12 - i * 4, Register.sp), Register.ss[i], i));
        }
        currentBasicBlock.push_back(new addri(Register.s0, Register.sp, it.globalframesize));

//        currentBasicBlock.push_back(new loadrinst(Register.s0, 0));
        isGlobal = true;
        for (VarDefStmt i : globalVars) {
            i.accept(this);
        }
        isGlobal = false;
        for (int i = 0; i < Register.ssSIZE; ++i) {
            currentBasicBlock.push_back(new load(new PointerRegister(it.globalframesize - 12 - i * 4, Register.sp), Register.ss[i], i));
        }
        currentBasicBlock.push_back(new load(s0Pointer, Register.s0));
        currentBasicBlock.push_back(new load(raPointer, Register.ra, true));
        currentBasicBlock.push_back(new addri(Register.sp, Register.sp, it.globalframesize));
        currentBasicBlock.push_back(new reter());

        for (FuncDef i : funcDefs) {
            i.accept(this);
        }
        for (ClassDef i : classDefs) {
            i.accept(this);//only to add all function of the class.
        }
    }

    @Override
    public void visit(ClassDef it) {
        it.constructDef.accept(this);
        for (var funcDef : it.funcDefs.values()) {
            funcDef.name = it.name + "__" + funcDef.name;
            funcDef.accept(this);
        }
    }

    //函数调用约定 ： s0 - 4 为 ra 记录跳转回去的地址，相当于jal中的link
    //s0 - 8 为 记录s0原来在哪里，本来跟跟sp的值相同，之后跳成跟sp不同，到时候在store回sp
    //s0 - 12 为 记录返回值 （同时a0也记录返回值，这里冗余了，可以删掉）
    //s0 - 16 起为函数传参
    //s0 - (16 + 4*参数个数)起为函数内定义的变量
    //函数栈结束
    @Override
    public void visit(FuncDef it) {
        currentBasicBlock = new BasicBlock("funcDef");
        fn = new Function(it.name, currentBasicBlock);
        irEntry.functions.add(fn);
        PointerRegister.min12 = new PointerRegister(-STACKSTARTSIZE);
        returnBlock = new BasicBlock("return");
        currentBasicBlock.push_back(new addri(Register.sp, Register.sp, -it.frameSize));
        PointerRegister raPointer = new PointerRegister(it.frameSize - 4, Register.sp);
        PointerRegister s0Pointer = new PointerRegister(it.frameSize - 8, Register.sp);
        currentBasicBlock.push_back(new store(raPointer, Register.ra, true));
        currentBasicBlock.push_back(new store(s0Pointer, Register.s0));
        for (int i = 0; i < Register.ssSIZE; ++i) {
            currentBasicBlock.push_back(new store(new PointerRegister(it.frameSize - 12 - i * 4 , Register.sp), Register.ss[i], i));
        }
        currentBasicBlock.push_back(new addri(Register.s0, Register.sp, it.frameSize));
        int pasz = 8;
        if(it.parameterTypes.size() < 8) pasz = it.parameterTypes.size();
        for(int i = 0; i < pasz; ++i){
            currentBasicBlock.push_back(new store(new PointerRegister(-STACKSTARTSIZE - 4*i - 4), Register.a[i]));
        }
        if ("main".equals(it.name)) {
            currentBasicBlock.push_back(new callfunc("global_init"));
        }

        it.body.stmts.forEach(s -> s.accept(this));
        if (currentBasicBlock.tailStmt == null) {
            if (Type.INT_TYPE.equals(it.returnType)) {
                currentBasicBlock.push_back(new loadinst(PointerRegister.min12, 0));
            }
            currentBasicBlock.push_back(new jump(returnBlock));
        }
        currentBasicBlock = returnBlock;
        fn.basicBlocks.add(returnBlock);
        if (it.returnType != null) {
            currentBasicBlock.push_back(new load(PointerRegister.min12, Register.a0));
        }
        for (int i = 0; i < Register.ssSIZE; ++i) {
            currentBasicBlock.push_back(new load(new PointerRegister(it.frameSize - 12 - i * 4, Register.sp), Register.ss[i], i));
        }
        currentBasicBlock.push_back(new load(s0Pointer, Register.s0));
        currentBasicBlock.push_back(new load(raPointer, Register.ra, true));
        currentBasicBlock.push_back(new addri(Register.sp, Register.sp, it.frameSize));
        currentBasicBlock.push_back(new reter());
    }

    @Override
    public void visit(VarDefStmt it) {
        int sz = it.names.size();
        for (int i = 0; i < sz; ++i) {
            PointerRegister pointerRegister = it.vars.get(i);
            if (pointerRegister.isGlobal) irEntry.globalpool.add(pointerRegister.val);
            if (it.init.get(i) != null) {
                it.init.get(i).accept(this);
                currentBasicBlock.push_back(new move(pointerRegister, it.init.get(i).pointerRegister));
            } else currentBasicBlock.push_back(new loadinst(pointerRegister, 0));//置null，要求是长位，32位都置0
        }
    }

    @Override
    public void visit(ReturnStmt it) {
        if (it.returnExpr != null) {
            it.returnExpr.accept(this);
            currentBasicBlock.push_back(new move(PointerRegister.min12, it.returnExpr.pointerRegister));
            currentBasicBlock.push_back(new load(it.returnExpr.pointerRegister, Register.a0));
        }
        currentBasicBlock.push_back(new jump(returnBlock));
        currentBasicBlock = new BasicBlock("return_after");
        fn.basicBlocks.add(currentBasicBlock);
    }


    @Override
    public void visit(ExprStmt it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(IfStmt it) {
        it.cond.accept(this);
        BasicBlock trueBranch = new BasicBlock("if_trueBranch"), destination = new BasicBlock("if_dest");
        if (it.falseNode != null) {
            BasicBlock falseBranch = new BasicBlock("if_falseBranch");
            currentBasicBlock.push_back(new branch(it.cond.pointerRegister, trueBranch, falseBranch));
            currentBasicBlock = trueBranch;
            it.trueNode.accept(this);
            currentBasicBlock.push_back(new jump(destination));
            currentBasicBlock = falseBranch;
            it.falseNode.accept(this);
            currentBasicBlock.push_back(new jump(destination));
            currentBasicBlock = destination;
            fn.basicBlocks.add(trueBranch);
            fn.basicBlocks.add(falseBranch);
            fn.basicBlocks.add(destination);
        } else {
            currentBasicBlock.push_back(new branch(it.cond.pointerRegister, trueBranch, destination));
            currentBasicBlock = trueBranch;
            it.trueNode.accept(this);
            currentBasicBlock.push_back(new jump(destination));
            currentBasicBlock = destination;
            fn.basicBlocks.add(trueBranch);
            fn.basicBlocks.add(destination);
        }
    }

    private boolean needtoread = true;


    @Override
    public void visit(AssignExpr it) {
        needtoread = false;
        it.lhs.accept(this);
        needtoread = true;
        it.rhs.accept(this);

        if (it.lhs instanceof VarExpr && ((VarExpr) it.lhs).proxyThis != null) it.lhs = ((VarExpr) it.lhs).proxyThis;
        if (it.lhs instanceof AddressNode) {
            currentBasicBlock.push_back(new load(((AddressNode) it.lhs).addressPointer, Register.index));
            currentBasicBlock.push_back(new move(new PointerRegister(0, Register.index), it.rhs.pointerRegister));//warning 跑move的时候不能改变Register的值。只要让index是独立的寄存器就可以了。
        } else currentBasicBlock.push_back(new move(it.lhs.pointerRegister, it.rhs.pointerRegister));
    }

    @Override
    public void visit(ConstExpr it) {
        int i = -999;
        if (it.type.isBasici32()) {
            if (Type.INT_TYPE.equals(it.type))
                i = Integer.parseInt(it.value);//大整数注意
            if (Type.BOOL_TYPE.equals(it.type))
                i = "true".equals(it.value) ? 1 : 0;
        } else if (Type.STRING_TYPE.equals(it.type)) {//String
            i = irEntry.stringpool.size();//这必然是大整数
            irEntry.stringpool.add(it.value);
        } else {
            assert (Type.NULL_TYPE.equals(it.type));
            i = 0;
        }
        if (Type.STRING_TYPE.equals(it.type))
            currentBasicBlock.push_back(new move(it.pointerRegister, new PointerRegister("str_" + (irEntry.stringpool.size() - 1), true)));
        else currentBasicBlock.push_back(new loadinst(it.pointerRegister, i));
    }

    @Override
    public void visit(BinaryExpr it) {
        if (Type.STRING_TYPE.equals(it.lhs.type)) {
            String op;
            switch (it.op) {
                case "+" -> op = "add";
                case "==" -> op = "eq";
                case "!=" -> op = "neq";
                case "<" -> op = "l";
                case "<=" -> op = "leq";
                case ">" -> op = "g";
                case ">=" -> op = "geq";
                default -> op = "";
            }
            FuncCallExpr proxyFuncCall = new FuncCallExpr("string__" + op);
            proxyFuncCall.argList.add(it.lhs);
            proxyFuncCall.argList.add(it.rhs);
            proxyFuncCall.pointerRegister = it.pointerRegister;
            proxyFuncCall.accept(this);
            return;
        }
        it.lhs.accept(this);
        if ("||".equals(it.op) || "&&".equals(it.op)) {
            Statement trueAssign, falseAssign;
            BasicBlock trueBranch = new BasicBlock("short_path_trueBranch"), falseBranch = new BasicBlock("short_path_falseBranch"), destination = new BasicBlock("short_path_destination");
            if ("||".equals(it.op)) {
                trueAssign = new loadinst(it.pointerRegister, 1);
                falseAssign = new move(it.pointerRegister, it.rhs.pointerRegister);
                currentBasicBlock.push_back(new branch(it.lhs.pointerRegister, trueBranch, falseBranch));
                currentBasicBlock = trueBranch;
                currentBasicBlock.push_back(trueAssign);
                currentBasicBlock.push_back(new jump(destination));
                currentBasicBlock = falseBranch;
                it.rhs.accept(this);
                currentBasicBlock.push_back(falseAssign);
                currentBasicBlock.push_back(new jump(destination));
            } else {
                trueAssign = new move(it.pointerRegister, it.rhs.pointerRegister);
                falseAssign = new loadinst(it.pointerRegister, 0);
                currentBasicBlock.push_back(new branch(it.lhs.pointerRegister, trueBranch, falseBranch));
                currentBasicBlock = trueBranch;
                it.rhs.accept(this);
                currentBasicBlock.push_back(trueAssign);
                currentBasicBlock.push_back(new jump(destination));
                currentBasicBlock = falseBranch;
                currentBasicBlock.push_back(falseAssign);
                currentBasicBlock.push_back(new jump(destination));
            }

            currentBasicBlock = destination;

            fn.basicBlocks.add(trueBranch);
            fn.basicBlocks.add(falseBranch);
            fn.basicBlocks.add(destination);
        } else {
            it.rhs.accept(this);
            currentBasicBlock.push_back(new binary(it.pointerRegister, it.lhs.pointerRegister, it.rhs.pointerRegister, it.op));
        }

    }

    @Override
    public void visit(SuiteStmt it) {
        it.stmts.forEach(s -> s.accept(this));
    }

    @Override
    public void visit(ConstructDef it) {
        it.proxyFunc.accept(this);
    }

    @Override
    public void visit(UnaryExpr it) {
        it.lhs.accept(this);
        switch (it.op) {
            case "+" -> currentBasicBlock.push_back(new move(it.pointerRegister, it.lhs.pointerRegister));
            case "-" -> currentBasicBlock.push_back(new binaryi(it.pointerRegister, it.lhs.pointerRegister, -1, "*"));
            case "~" -> currentBasicBlock.push_back(new binaryi(it.pointerRegister, it.lhs.pointerRegister, -1, "^"));
            case "!" -> currentBasicBlock.push_back(new binaryi(it.pointerRegister, it.lhs.pointerRegister, 1, "^"));
        }
    }

    @Override
    public void visit(PrefixExpr it) {
        it.lhs.accept(this);

        assert (Type.INT_TYPE.equals(it.lhs.type));
        if (it.lhs instanceof VarExpr && ((VarExpr) it.lhs).proxyThis != null) it.lhs = ((VarExpr) it.lhs).proxyThis;
        if (it.lhs instanceof AddressNode) {
            currentBasicBlock.push_back(new binaryi(it.lhs.pointerRegister, it.lhs.pointerRegister, 1, it.op.substring(1)));
            currentBasicBlock.push_back(new load(((AddressNode) it.lhs).addressPointer, Register.index));
            currentBasicBlock.push_back(new move(new PointerRegister(0, Register.index), it.lhs.pointerRegister));
        } else
            currentBasicBlock.push_back(new binaryi(it.lhs.pointerRegister, it.lhs.pointerRegister, 1, it.op.substring(1)));
    }

    @Override
    public void visit(SuffixExpr it) {
        it.lhs.accept(this);

        assert (Type.INT_TYPE.equals(it.lhs.type));
        currentBasicBlock.push_back(new move(it.pointerRegister, it.lhs.pointerRegister));
        if (it.lhs instanceof VarExpr && ((VarExpr) it.lhs).proxyThis != null) it.lhs = ((VarExpr) it.lhs).proxyThis;
        if (it.lhs instanceof AddressNode) {
            currentBasicBlock.push_back(new load(((AddressNode) it.lhs).addressPointer, Register.index));
            currentBasicBlock.push_back(new binaryi(new PointerRegister(0, Register.index), it.lhs.pointerRegister, 1, it.op.substring(1)));
        } else
            currentBasicBlock.push_back(new binaryi(it.lhs.pointerRegister, it.lhs.pointerRegister, 1, it.op.substring(1)));
    }

    @Override
    public void visit(ThisExpr it) {
        //cautious copied from VarExpr
        assert (it.pointerRegister != null);
    }

    @Override
    public void visit(NewArrayExpr it) {
        it.dims.forEach(dim -> dim.accept(this));
        currentBasicBlock.push_back(new malloci(it.tempPointer, 4 * (it.dims.size() + 1)));
        currentBasicBlock.push_back(new load(it.tempPointer, Register.index));
        PointerRegister p = new PointerRegister(0, Register.index);
        currentBasicBlock.push_back(new loadinst(p, it.dims.size()));
        for (Expr dim : it.dims) {
            p.address += 4;
            currentBasicBlock.push_back(new move(new PointerRegister(p.address, Register.index), dim.pointerRegister));
        }
        p.address = 0;
        int spConst = -STACKSTARTSIZE;

        PointerRegister pointerRegister = new PointerRegister(spConst -= 4, Register.sp);
        currentBasicBlock.push_back(new store(pointerRegister, Register.index));
        currentBasicBlock.push_back(new load(pointerRegister, Register.a0));

//        PointerRegister pointerRegister2 = new PointerRegister(spConst -= 4, Register.sp);
//        currentBasicBlock.push_back(new move(pointerRegister2, it.pointerRegister));
//        currentBasicBlock.push_back(new load(pointerRegister2, Register.a1));

        PointerRegister pointerRegister3 = new PointerRegister(spConst -= 4, Register.sp);
        currentBasicBlock.push_back(new loadinst(pointerRegister3, 4));
        currentBasicBlock.push_back(new load(pointerRegister3, Register.a1));

        currentBasicBlock.push_back(new callfunc("ir_new_array"));

        currentBasicBlock.push_back(new store(it.pointerRegister, Register.a0));

//        FuncCallExpr funcCallExpr = new FuncCallExpr("ir_new_array");

//        for(int offset = 0;)
//
//            malloc p, size+1
//            store p, len, p0, p1, ...
//
//        func(p, ret, i):
//        if i < len return;
//            malloc ret p(i)
//            for j = 0 - p(i)
//                func(p,(*ret)(j), i+1)

    }

    @Override
    public void visit(ForStmt it) {
        BasicBlock cond = new BasicBlock("for_cond"),
                incr = new BasicBlock("for_increase"),
                body = new BasicBlock("for_body"),
                dest = new BasicBlock("for_dest");


        continueTo.add(incr);
        breakTo.add(dest);

        if (it.init != null) it.init.accept(this);
        currentBasicBlock.push_back(new jump(cond));

        fn.basicBlocks.add(cond);
        currentBasicBlock = cond;
        if (it.cond != null) {
            it.cond.accept(this);
            currentBasicBlock.push_back(new branch(it.cond.pointerRegister, body, dest));
        } else {
            currentBasicBlock.push_back(new jump(body));
        }
        fn.basicBlocks.add(body);
        currentBasicBlock = body;
        if (it.body != null) {
            if (it.body instanceof SuiteStmt)
                ((SuiteStmt) it.body).stmts.forEach(stmt -> stmt.accept(this));
            else it.body.accept(this);
        }
        currentBasicBlock.push_back(new jump(incr));

        fn.basicBlocks.add(incr);
        currentBasicBlock = incr;
        if (it.incr != null) it.incr.accept(this);
        currentBasicBlock.push_back(new jump(cond));

        fn.basicBlocks.add(dest);
        currentBasicBlock = dest;

        continueTo.pop();
        breakTo.pop();
    }

    @Override
    public void visit(ContinueStmt it) {
        currentBasicBlock.push_back(new jump(continueTo.peek()));
        BasicBlock afterContinueBlock = new BasicBlock("after_continue");
        fn.basicBlocks.add(afterContinueBlock);
        currentBasicBlock = afterContinueBlock;
    }

    @Override
    public void visit(BreakStmt it) {
        currentBasicBlock.push_back(new jump(breakTo.peek()));
        BasicBlock afterBreakBlock = new BasicBlock("after_break");
        fn.basicBlocks.add(afterBreakBlock);
        currentBasicBlock = afterBreakBlock;
    }


    @Override
    public void visit(IndexExpr it) {
        boolean patyneedtoread = needtoread;
        needtoread = true;
        it.lhs.accept(this);
        it.rhs.accept(this);

        currentBasicBlock.push_back(new binaryi(((IndexExpr) it).tempPointer, it.rhs.pointerRegister, 2, "<<"));
        currentBasicBlock.push_back(new binary(it.addressPointer, it.lhs.pointerRegister, ((IndexExpr) it).tempPointer, "+"));
        if(patyneedtoread) {
            currentBasicBlock.push_back(new load(it.addressPointer, Register.index));
            currentBasicBlock.push_back(new move(it.pointerRegister, new PointerRegister(0, Register.index)));
        }else{

        }
    }

    @Override
    public void visit(MemberExpr it) {
        //copy from up
        boolean patyneedtoread = needtoread;
        needtoread = true;
        it.lhs.accept(this);

        currentBasicBlock.push_back(new binaryi(it.addressPointer, it.lhs.pointerRegister, it.lhs.type.getClassDef().offsets.get(it.rhs.name), "+"));
        if(patyneedtoread) {
            currentBasicBlock.push_back(new load(it.addressPointer, Register.index));
            currentBasicBlock.push_back(new move(it.pointerRegister, new PointerRegister(0, Register.index)));
        }
    }

    @Override
    public void visit(FuncCallExpr it) {
        //这里本应该进行一堆store，这是为了方便的写法
        it.argList.forEach(expr -> {
            expr.accept(this);
        });
        int spConst = -STACKSTARTSIZE;
        int i = 0;
        for (var expr : it.argList) {
            if (i < 8) {
                currentBasicBlock.push_back(new load(expr.pointerRegister, Register.a[i]));
                spConst -= 4;
            } else{
                PointerRegister pointerRegister = new PointerRegister(spConst -= 4, Register.sp);
                currentBasicBlock.push_back(new move(pointerRegister, expr.pointerRegister));
            }
            ++i;
        }
        ;
        currentBasicBlock.push_back(new callfunc(it.name));
        currentBasicBlock.push_back(new store(it.pointerRegister, Register.a0));
    }

    @Override
    public void visit(VarExpr it) {
        assert (it.pointerRegister != null);
        if (it.proxyThis != null) {
            it.proxyThis.accept(this);
        }
    }

    @Override
    public void visit(LambdaExpr it) {
        //pass
    }

    @Override
    public void visit(EmptyStmt it) {
        //pass
    }

    private NewClassExpr recordNewClassExpr;//ugly fix

    @Override
    public void visit(NewClassExpr it) {
        if (it == recordNewClassExpr) return;
        recordNewClassExpr = it;
        ClassDef classDef = it.type.getClassDef();
        currentBasicBlock.push_back(new malloci(it.pointerRegister, classDef.classSize));
        //注：malloc的作用现在为：返回一个指向某new出来的地址的，存到PointerRegister的reg部分（而不是address部分）；
        FuncCallExpr proxyFuncCall = new FuncCallExpr(classDef.name);
//        VarExpr newclassvar = new VarExpr("newclass");
//        newclassvar.entity = it.entity;

        proxyFuncCall.argList.add(it);
        proxyFuncCall.pointerRegister = it.funcPointer;
        proxyFuncCall.accept(this);
    }

    @Override
    public void visit(MemberFuncCallExpr it) {
        if (it.proxyFuncCall == null)
            System.out.println(it.name);
        it.proxyFuncCall.accept(this);
    }

}
