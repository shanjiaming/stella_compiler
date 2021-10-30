package AST;

abstract public class ASTVisitor {

    public void visit(FuncDef funcDefNode){}

    public void visit(ClassDef classDef){}

    public void visit(VarDefStmt varDefStmt){}

    public void visit(Program programNode){}

    public void visit(SuiteStmt suiteStmtNode){}

    public void visit(ConstructDef constructDef){}

    public void visit(BinaryExpr binaryExpr){}

    public void visit(UnaryExpr unaryExpr){}

    public void visit(PrefixExpr prefixExpr){}

    public void visit(SuffixExpr suffixExpr){}

    public void visit(IfStmt ifStmt){}

    public void visit(ThisExpr thisExpr){}

    public void visit(NewArrayExpr newArrayExpr){}

    public void visit(ForStmt forStmt){}

    public void visit(ContinueStmt continueStmt){}

    public void visit(BreakStmt breakStmt){}

    public void visit(ReturnStmt returnStmt){}

    public void visit(IndexExpr indexExpr){}

    public void visit(MemberExpr memberExpr){}

    public void visit(FuncCallExpr funcCallExpr){}

    public void visit(VarExpr varExpr){}

    public void visit(LambdaExpr lambdaExpr){}

    public void visit(ConstExpr constExpr){}

    public void visit(EmptyStmt emptyStmt){}

    public void visit(AssignExpr assignExpr){}

    public void visit(NewClassExpr newClassExpr){}

    public void visit(MemberFuncCallExpr memberFuncCallExpr){}
}
