// Generated from D:/pg/compiler/workspace/src\Mxstar.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxstarParser}.
 */
public interface MxstarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#declare}.
	 * @param ctx the parse tree
	 */
	void enterDeclare(MxstarParser.DeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#declare}.
	 * @param ctx the parse tree
	 */
	void exitDeclare(MxstarParser.DeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterBaseType(MxstarParser.BaseTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitBaseType(MxstarParser.BaseTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#typename}.
	 * @param ctx the parse tree
	 */
	void enterTypename(MxstarParser.TypenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#typename}.
	 * @param ctx the parse tree
	 */
	void exitTypename(MxstarParser.TypenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#constType}.
	 * @param ctx the parse tree
	 */
	void enterConstType(MxstarParser.ConstTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#constType}.
	 * @param ctx the parse tree
	 */
	void exitConstType(MxstarParser.ConstTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#newExpr}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(MxstarParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#newExpr}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(MxstarParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varDeclare}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclare(MxstarParser.VarDeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varDeclare}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclare(MxstarParser.VarDeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#initialPart}.
	 * @param ctx the parse tree
	 */
	void enterInitialPart(MxstarParser.InitialPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#initialPart}.
	 * @param ctx the parse tree
	 */
	void exitInitialPart(MxstarParser.InitialPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#classDeclare}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclare(MxstarParser.ClassDeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#classDeclare}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclare(MxstarParser.ClassDeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#classIdentity}.
	 * @param ctx the parse tree
	 */
	void enterClassIdentity(MxstarParser.ClassIdentityContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#classIdentity}.
	 * @param ctx the parse tree
	 */
	void exitClassIdentity(MxstarParser.ClassIdentityContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#constructDeclare}.
	 * @param ctx the parse tree
	 */
	void enterConstructDeclare(MxstarParser.ConstructDeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#constructDeclare}.
	 * @param ctx the parse tree
	 */
	void exitConstructDeclare(MxstarParser.ConstructDeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#funcDeclare}.
	 * @param ctx the parse tree
	 */
	void enterFuncDeclare(MxstarParser.FuncDeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#funcDeclare}.
	 * @param ctx the parse tree
	 */
	void exitFuncDeclare(MxstarParser.FuncDeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MxstarParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MxstarParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(MxstarParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(MxstarParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MxstarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MxstarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(MxstarParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(MxstarParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#lambda}.
	 * @param ctx the parse tree
	 */
	void enterLambda(MxstarParser.LambdaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#lambda}.
	 * @param ctx the parse tree
	 */
	void exitLambda(MxstarParser.LambdaContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(MxstarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(MxstarParser.ExprContext ctx);
}