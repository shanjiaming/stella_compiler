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
    private BasicBlock currentBasicBlock;
    private final IREntry irEntry;
    private int basicBlockCounter = 0;
    private Stack<BasicBlock> continueTo = new Stack<>();
    private Stack<BasicBlock> breakTo = new Stack<>();

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
        for (VarDefStmt i : globalVars) {
            i.accept(this);
        }
        currentBasicBlock.push_back(new call("main"));
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
    }

    @Override
    public void visit(FuncDef it) {
        currentBasicBlock = new BasicBlock("funcDef");
        fn = new Function(it.name, currentBasicBlock);
        irEntry.functions.add(fn);
        alloca.remebersp();
//        currentBasicBlock.push_back(new load(sp, sp + 4, sp + 8, ...));
        //需要load参数，当做是函数一开始就定义了这些参数一般。这些参数实际上是funcall store进去的，在store之前记录了sp因此it知道从哪里取。

        it.body.stmts.forEach(s -> s.accept(this));
        alloca.resetsp();

    }

    @Override
    public void visit(VarDefStmt it) {
        int sz = it.names.size();
        for (int i = 0; i < sz; ++i) {
            PointerRegister pointerRegister = it.vars.get(i);
            currentBasicBlock.push_back(new alloca(pointerRegister, it.varType.size()));
            it.init.get(i).accept(this);
            currentBasicBlock.push_back(new store(pointerRegister, it.init.get(i).entity));
        }
    }

    @Override
    public void visit(ReturnStmt it) {
        //TODO 要store到一个地方去，如sp。但是用专门寄存器保存也非常ok。用永久寄存器吧，那么现在有两个永久寄存器，sp和ret。
        it.returnExpr.accept(this);
        currentBasicBlock.push_back(new ret(it.returnExpr.entity));
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
        //FIXME pointerregister也有引用型跟值型的区别。像class x，array a，string s，class a.array，matrix[row]是引用型，如果是一维数组那么array[0]或是int x。是值型。在作为左值或右值的时候都，引用型提供地址，而值型提供值。
        //但是因为ASTNode里已经记录了Type，这样在AST转IR中构造IR语句的那一步就知道构造出的语句应当是什么类型的了，而不需要记录额外的信息（有可能只是在一些IR语句中加一个输出格式是值型还是引用型的bool值，有可能不）
    }

    @Override
    public void visit(ConstExpr it) {
        if (it.type.isBasici32()) {
            if (Type.INT_TYPE.equals(it.type))
                it.entity = new Constant(Integer.parseInt(it.value));
            if (Type.BOOL_TYPE.equals(it.type))
                it.entity = new Constant("true".equals(it.value) ? 1 : 0);
            assert (false);
        } else {//String
            PointerRegister pointerRegister = new PointerRegister();
            it.entity = pointerRegister;
            currentBasicBlock.push_back(new malloc(pointerRegister, (1 + it.value.length()) * 32));
            currentBasicBlock.push_back(new store(pointerRegister, new Constant(it.value.length())));
            for (char c : it.value.toCharArray()) {
                currentBasicBlock.push_back(new store(pointerRegister, new Constant(c)));
            }//FIXME fix string representation
        }
    }

    @Override
    public void visit(BinaryExpr it) {
        it.lhs.accept(this);
        if ("||".equals(it.op) || "&&".equals(it.op)) {
            assign trueAssign, falseAssign;
            BasicBlock trueBranch = new BasicBlock("short_path_trueBranch"), falseBranch = new BasicBlock("short_path_falseBranch"), destination = new BasicBlock("short_path_destination");
            if ("||".equals(it.op)) {
                trueAssign = new assign((Register) it.entity, new Constant(1));
                falseAssign = new assign((Register) it.entity, it.rhs.entity);
                currentBasicBlock.push_back(new branch(it.lhs.entity, trueBranch, falseBranch));
                currentBasicBlock = trueBranch;
                currentBasicBlock.push_back(trueAssign);
                currentBasicBlock.push_back(new jump(destination));
                currentBasicBlock = falseBranch;
                it.rhs.accept(this);
                currentBasicBlock.push_back(falseAssign);
                currentBasicBlock.push_back(new jump(destination));
            } else {
                trueAssign = new assign((Register) it.entity, it.rhs.entity);
                falseAssign = new assign((Register) it.entity, new Constant(0));
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
        alloca.remebersp();
        it.stmts.forEach(s -> s.accept(this));
        alloca.resetsp();
    }

    @Override
    public void visit(ConstructDef it) {
    }

    @Override
    public void visit(UnaryExpr it) {
        it.lhs.accept(this);
//        currentBasicBlock.push_back(new binary((Register) it.lhs.entity, it.lhs.entity, new Constant(1), "+"));
    }

    @Override
    public void visit(PrefixExpr it) {
        it.lhs.accept(this);
        assert (Type.INT_TYPE.equals(it.lhs.type));
//        Register register = new Register();
//        currentBasicBlock.push_back(new load((PointerRegister) it.lhs.entity, (Register) it.lhs.entity));
        currentBasicBlock.push_back(new binary((Register) it.lhs.entity, it.lhs.entity, new Constant(1), "+"));
        currentBasicBlock.push_back(new store((PointerRegister) it.lhs.entity, it.lhs.entity));
    }

    @Override
    public void visit(SuffixExpr it) {
        it.lhs.accept(this);
        assert (Type.INT_TYPE.equals(it.lhs.type));
        currentBasicBlock.push_back(new assign((Register) it.entity, it.lhs.entity));
        currentBasicBlock.push_back(new binary((Register) it.lhs.entity, it.lhs.entity, new Constant(1), "+"));
        currentBasicBlock.push_back(new store((PointerRegister) it.lhs.entity, it.lhs.entity));
    }

    @Override
    public void visit(ThisExpr it) {
        //
    }

    @Override
    public void visit(NewArrayExpr it) {
    }

    @Override
    public void visit(ForStmt it) {
        BasicBlock cond = new BasicBlock("for_cond"),
                incr = new BasicBlock("for_increase"),
                body = new BasicBlock("for_body"),
                dest = new BasicBlock("for_dest");

        alloca.remebersp();

        continueTo.add(incr);
        breakTo.add(dest);

        if (it.init != null) it.init.accept(this);
        currentBasicBlock.push_back(new jump(cond));

        fn.basicBlocks.add(cond);
        currentBasicBlock = cond;
        if (it.cond != null) it.cond.accept(this);
        currentBasicBlock.push_back(new branch(it.cond.entity, body, dest));

        fn.basicBlocks.add(body);
        currentBasicBlock = body;
        if (it.body != null) {
            if (it.body instanceof SuiteStmt)
                ((SuiteStmt) it.body).stmts.forEach(stmt -> stmt.accept(this));
            else  it.body.accept(this);
        }
        currentBasicBlock.push_back(new jump(incr));

        fn.basicBlocks.add(incr);
        currentBasicBlock = incr;
        if (it.incr != null) it.incr.accept(this);
        currentBasicBlock.push_back(new jump(cond));

        alloca.resetsp();
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
    }

    @Override
    public void visit(MemberExpr it) {
    }

    @Override
    public void visit(FuncCallExpr it) {
        //这里本应该进行一堆store，这是为了方便的写法
        it.argList.forEach(expr -> expr.accept(this));
        currentBasicBlock.push_back(new call(it.name, it.argList));
    }

    @Override
    public void visit(VarExpr it) {
        if (it.type.isBasici32())
            currentBasicBlock.push_back(new load(((PointerRegister) it.entity), ((PointerRegister) it.entity)));//因为这个it.entity明明是临时值，这就不ssa了，不过再说吧。
    }

    @Override
    public void visit(LambdaExpr it) {
        //pass
    }

    @Override
    public void visit(EmptyStmt it) {
        //pass
    }

    @Override
    public void visit(NewClassExpr it) {
    }

    @Override
    public void visit(MemberFuncCallExpr it) {
    }

}
