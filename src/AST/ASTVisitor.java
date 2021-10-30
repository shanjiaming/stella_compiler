package AST;

public interface ASTVisitor {
    void visit(FuncDef funcDefNode);

    void visit(ClassDef classDef);

    void visit(VarDefStmt varDefStmt);

    void visit(Program programNode);

    void visit(SuiteStmt suiteStmtNode);

    void visit(ConstructDef constructDef);

    void visit(BinaryExpr binaryExpr);

    void visit(UnaryExpr unaryExpr);

    void visit(PrefixExpr prefixExpr);

    void visit(SuffixExpr suffixExpr);

    void visit(IfStmt ifStmt);

    void visit(ThisExpr thisExpr);

    void visit(NewArrayExpr newArrayExpr);

    void visit(ForStmt forStmt);

    void visit(ContinueStmt continueStmt);

    void visit(BreakStmt breakStmt);

    void visit(ReturnStmt returnStmt);

    void visit(IndexExpr indexExpr);

    void visit(MemberExpr memberExpr);

    void visit(FuncCallExpr funcCallExpr);

    void visit(VarExpr varExpr);

    void visit(LambdaExpr lambdaExpr);

    void visit(ConstExpr constExpr);

    void visit(EmptyStmt emptyStmt);

    void visit(AssignExpr assignExpr);

    void visit(NewClassExpr newClassExpr);

    void visit(MemberFuncCallExpr memberFuncCallExpr);
}
