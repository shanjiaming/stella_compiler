package Backend;

import AST.*;
import IR.*;
import IR.Instruction.*;
import IR.Constant;
import Util.Type;

import java.util.ArrayList;
import java.util.Stack;


public class IRBuilder extends ASTVisitor {
    private Function fn;
    private BasicBlock currentBasicBlock;//todo 用mv, li 把指令规范化，消除const带来的影响。
    private BasicBlock returnBlock;
    private final IREntry irEntry;
    private Stack<BasicBlock> continueTo = new Stack<>();
    private Stack<BasicBlock> breakTo = new Stack<>();
    private int s0Const;

    private void memlize(Register register){

    }

    public IRBuilder(IREntry irEntry) {
        this.irEntry = irEntry;
    }

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
        currentBasicBlock = new BasicBlock("initialize");
        fn = new Function("initialize", currentBasicBlock);
        irEntry.functions.add(fn);
        int globalSize = 0;
        for (VarDefStmt i : globalVars) {
            globalSize += 4;
        }
        currentBasicBlock.push_back(new loadinst(Register.sp, new Constant(-globalSize)));
        for (VarDefStmt i : globalVars) {
            i.accept(this);
        }
        currentBasicBlock.push_back(new callfunc("main"));
        for (FuncDef i : funcDefs) {
            i.accept(this);
        }
        for (ClassDef i : classDefs) {
            i.accept(this);//only to add all function of the class.
        }
    }

    @Override
    public void visit(ClassDef it) {
//TODO
        it.constructDef.accept(this);
        for (var funcDef : it.funcDefs.values()){
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
        s0Const = -12;
        PointerRegister.min12 = new PointerRegister(-12);
        returnBlock = new BasicBlock("return");
        currentBasicBlock.push_back(new binary(Register.sp, Register.sp, new Constant(-it.frameSize), "+"));
        PointerRegister raPointer = new PointerRegister(it.frameSize - 4, Register.sp);
        PointerRegister s0Pointer = new PointerRegister(it.frameSize - 8, Register.sp);
        currentBasicBlock.push_back(new store(raPointer, Register.ra));
        currentBasicBlock.push_back(new store(s0Pointer, Register.s0));
        currentBasicBlock.push_back(new binary(Register.s0, Register.sp, new Constant(it.frameSize), "+"));
        for (var para : it.vars){
            para.address = (s0Const -= 4);
        }
        it.body.stmts.forEach(s -> s.accept(this));
        currentBasicBlock = returnBlock;
        fn.basicBlocks.add(returnBlock);
        if (it.returnType != null) {
            currentBasicBlock.push_back(new load(PointerRegister.min12, Register.a0));
        }
        currentBasicBlock.push_back(new load(s0Pointer, Register.s0));
        currentBasicBlock.push_back(new load(raPointer, Register.ra));
        currentBasicBlock.push_back(new binary(Register.sp, Register.sp, new Constant(it.frameSize), "+"));
        currentBasicBlock.push_back(new reter());
    }

    @Override
    public void visit(VarDefStmt it) {
        int sz = it.names.size();
        for (int i = 0; i < sz; ++i) {
            PointerRegister pointerRegister = it.vars.get(i);
            pointerRegister.address = (s0Const -= 4);
            if (it.init.get(i) != null) {
                it.init.get(i).accept(this);
                currentBasicBlock.push_back(new store(pointerRegister, it.init.get(i).entity));
            }else currentBasicBlock.push_back(new store(pointerRegister, new Constant(0)));//置null，要求是长位，32位都置0
        }
    }

    @Override
    public void visit(ReturnStmt it) {
        //TODO 要store到一个地方去，如sp。但是用专门寄存器保存也非常ok。用永久寄存器吧，那么现在有两个永久寄存器，sp和ret。

        if (it.returnExpr != null) {
            it.returnExpr.accept(this);
            currentBasicBlock.push_back(new store(PointerRegister.min12, it.returnExpr.entity));
            currentBasicBlock.push_back(new move(Register.a0, it.returnExpr.entity));
        }
        currentBasicBlock.push_back(new jump(returnBlock));
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
            currentBasicBlock.push_back(new branch(it.cond.entity, trueBranch, falseBranch));
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
            currentBasicBlock.push_back(new branch(it.cond.entity, trueBranch, destination));
            currentBasicBlock = trueBranch;
            it.trueNode.accept(this);
            currentBasicBlock.push_back(new jump(destination));
            currentBasicBlock = destination;
            fn.basicBlocks.add(trueBranch);
            fn.basicBlocks.add(destination);
        }
    }

    @Override
    public void visit(AssignExpr it) {
        //maybe have problem
        assert (it.lhs.entity instanceof PointerRegister);
        it.lhs.accept(this);
        it.rhs.accept(this);
        currentBasicBlock.push_back(new store((PointerRegister) it.lhs.entity, it.rhs.entity));
        //但是只要引用类型都是存的二级指针，引用和值就可以统一了。
        //但是因为ASTNode里已经记录了Type，这样在AST转IR中构造IR语句的那一步就知道构造出的语句应当是什么类型的了，而不需要记录额外的信息（有可能只是在一些IR语句中加一个输出格式是值型还是引用型的bool值，有可能不）
    }

    @Override
    public void visit(ConstExpr it) {//TODO 小心大整数，那不是立即数所能容纳的！
        if (it.type.isBasici32()) {
            if (Type.INT_TYPE.equals(it.type))
                it.entity = new Constant(Integer.parseInt(it.value));//大整数注意
            if (Type.BOOL_TYPE.equals(it.type))
                it.entity = new Constant("true".equals(it.value) ? 1 : 0);
        } else if (Type.STRING_TYPE.equals(it.type)) {//String
            PointerRegister pointerRegister = new PointerRegister();
            it.entity = new Constant(irEntry.stringpool.size());//这必然是大整数
            irEntry.stringpool.add(it.value);
        }else{
            assert (Type.NULL_TYPE.equals(it.type));
            it.entity = new Constant(0);
        }
    }

    @Override
    public void visit(BinaryExpr it) {
        if(Type.STRING_TYPE.equals(it.lhs.type)){
            String op;
            switch (it.op){
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
            proxyFuncCall.entity = it.entity;
            proxyFuncCall.accept(this);
            return;
        }
        it.lhs.accept(this);
        if ("||".equals(it.op) || "&&".equals(it.op)) {
            Statement trueAssign, falseAssign;
            BasicBlock trueBranch = new BasicBlock("short_path_trueBranch"), falseBranch = new BasicBlock("short_path_falseBranch"), destination = new BasicBlock("short_path_destination");
            if ("||".equals(it.op)) {
                trueAssign = new loadinst((Register) it.entity, new Constant(1));
                falseAssign = new move((Register) it.entity, (Register) it.rhs.entity);
                currentBasicBlock.push_back(new branch(it.lhs.entity, trueBranch, falseBranch));
                currentBasicBlock = trueBranch;
                currentBasicBlock.push_back(trueAssign);
                currentBasicBlock.push_back(new jump(destination));
                currentBasicBlock = falseBranch;
                it.rhs.accept(this);
                currentBasicBlock.push_back(falseAssign);
                currentBasicBlock.push_back(new jump(destination));
            } else {
                trueAssign = new move((Register) it.entity, (Register) it.rhs.entity);
                falseAssign = new loadinst((Register) it.entity, new Constant(0));
                currentBasicBlock.push_back(new branch(it.lhs.entity, trueBranch, falseBranch));
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
            currentBasicBlock.push_back(new binary((Register) it.entity, it.lhs.entity, it.rhs.entity, it.op));
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
            case "+" -> currentBasicBlock.push_back(new move((Register) it.entity, it.lhs.entity));
            case "-" -> currentBasicBlock.push_back(new binary((Register) it.entity, new Constant(0), it.lhs.entity, "-"));
            case "~" -> currentBasicBlock.push_back(new binary((Register) it.entity, new Constant(-1), it.lhs.entity, "-"));
            case "!" -> currentBasicBlock.push_back(new binary((Register) it.entity, new Constant(1), it.lhs.entity, "^"));
        }
    }

    @Override
    public void visit(PrefixExpr it) {
        it.lhs.accept(this);
        assert (Type.INT_TYPE.equals(it.lhs.type));
//        Register register = new Register();
//        currentBasicBlock.push_back(new load((PointerRegister) it.lhs.entity, (Register) it.lhs.entity));
        currentBasicBlock.push_back(new binary((Register) it.lhs.entity, it.lhs.entity, new Constant(1), it.op.substring(1)));
        currentBasicBlock.push_back(new store((PointerRegister) it.lhs.entity, it.lhs.entity));
    }

    @Override
    public void visit(SuffixExpr it) {
        it.lhs.accept(this);
        assert (Type.INT_TYPE.equals(it.lhs.type));
        currentBasicBlock.push_back(new move((Register) it.entity, (Register) it.lhs.entity));
        currentBasicBlock.push_back(new binary((Register) it.lhs.entity, it.lhs.entity, new Constant(1), it.op.substring(1)));
        currentBasicBlock.push_back(new store((PointerRegister) it.lhs.entity, it.lhs.entity));
    }

    @Override
    public void visit(ThisExpr it) {
        //cautious copied from VarExpr
        assert (it.entity != null);
        currentBasicBlock.push_back(new load(((PointerRegister) it.entity), ((PointerRegister) it.entity)));//因为这个it.entity明明是临时值，这就不ssa了，不过再说吧。
    }

    @Override
    public void visit(NewArrayExpr it) {
//        ClassDef classDef = it.type.getClassDef();
        it.dims.forEach(dim -> dim.accept(this));
        Register r = new Register();
        currentBasicBlock.push_back(new malloc(r, 4*(it.dims.size() + 1)));
        PointerRegister p = new PointerRegister(0,r);
        currentBasicBlock.push_back(new store(p, new Constant(it.dims.size())));
        for (Expr dim : it.dims){
            p.address += 4;
            currentBasicBlock.push_back(new store(new PointerRegister(p.address, r), dim.entity));
        }
        p.address = 0;
//        currentBasicBlock.push_back(new load(p,p));
        int spConst = -12;

        PointerRegister pointerRegister = new PointerRegister(spConst -= 4, Register.sp);
        currentBasicBlock.push_back(new store(pointerRegister, r));

        PointerRegister pointerRegister2 = new PointerRegister(spConst -= 4, Register.sp);
        currentBasicBlock.push_back(new store(pointerRegister2, it.entity));

        PointerRegister pointerRegister3 = new PointerRegister(spConst -= 4, Register.sp);
        currentBasicBlock.push_back(new store(pointerRegister3, new Constant(4)));

        currentBasicBlock.push_back(new callfunc("ir_new_array"));
        currentBasicBlock.push_back(new load(pointerRegister2, (Register) it.entity));

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
            currentBasicBlock.push_back(new branch(it.cond.entity, body, dest));
        }else{
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
        it.lhs.accept(this);
        it.rhs.accept(this);

        Register pointeroffset = new Register();

        PointerRegister pointerRegister = (PointerRegister)it.entity;
        pointerRegister.address = 0;
        pointerRegister.offset = pointeroffset;
        Register temp4 = new Register();
        currentBasicBlock.push_back(new binary(temp4, it.rhs.entity, new Constant(2), "<<"));
        currentBasicBlock.push_back(new binary(pointeroffset, it.lhs.entity, temp4, "+"));
        currentBasicBlock.push_back(new load(pointerRegister, pointerRegister));

    }

    @Override
    public void visit(MemberExpr it) {
        //copy from up
        it.lhs.accept(this);
        PointerRegister pointerRegister = (PointerRegister)it.entity;
        assert (pointerRegister != null);
        pointerRegister.address = 0;
        PointerRegister pointeroffset = new PointerRegister();
        currentBasicBlock.push_back(new binary(pointeroffset, it.lhs.entity, new Constant(it.lhs.type.getClassDef().offsets.get(it.rhs.name)), "+"));
        pointerRegister.offset = pointeroffset;
        currentBasicBlock.push_back(new load(pointerRegister, pointerRegister));
    }

    @Override
    public void visit(FuncCallExpr it) {
        //这里本应该进行一堆store，这是为了方便的写法
        it.argList.forEach(expr -> {
            expr.accept(this);
        });
        int spConst = -12;
        for (var expr : it.argList){
            PointerRegister pointerRegister = new PointerRegister(spConst -= 4, Register.sp);
            currentBasicBlock.push_back(new store(pointerRegister, expr.entity));
        };
        currentBasicBlock.push_back(new callfunc(it.name));
        currentBasicBlock.push_back(new move((Register) it.entity, Register.a0));
    }

    @Override
    public void visit(VarExpr it) {
        assert (it.entity != null);
        if(it.proxyThis != null) {
            it.proxyThis.accept(this);
        }
        else currentBasicBlock.push_back(new load(((PointerRegister) it.entity), ((Register) it.entity)));//因为这个it.entity明明是临时值，这就不ssa了，不过再说吧。
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
        currentBasicBlock.push_back(new malloc((Register)it.entity, new Constant(classDef.classSize)));
        //注：malloc的作用现在为：返回一个指向某new出来的地址的，存到PointerRegister的reg部分（而不是address部分）；
        FuncCallExpr proxyFuncCall = new FuncCallExpr(classDef.name);
//        VarExpr newclassvar = new VarExpr("newclass");
//        newclassvar.entity = it.entity;

        proxyFuncCall.argList.add(it);
        proxyFuncCall.entity = new PointerRegister();//相当于舍弃返回值
        proxyFuncCall.accept(this);
    }

    @Override
    public void visit(MemberFuncCallExpr it) {
        if(it.proxyFuncCall == null)
            System.out.println(it.name);
        it.proxyFuncCall.accept(this);
    }

}
