// Generated from D:/pg/compiler/workspace/src\Mxstar.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxstarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxstarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#declare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclare(MxstarParser.DeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(MxstarParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#typename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypename(MxstarParser.TypenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#constType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstType(MxstarParser.ConstTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#newExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpr(MxstarParser.NewExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#varDeclare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclare(MxstarParser.VarDeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#initialPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitialPart(MxstarParser.InitialPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#classDeclare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclare(MxstarParser.ClassDeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#classIdentity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassIdentity(MxstarParser.ClassIdentityContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#constructDeclare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructDeclare(MxstarParser.ConstructDeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#funcDeclare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclare(MxstarParser.FuncDeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(MxstarParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(MxstarParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MxstarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(MxstarParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#lambda}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambda(MxstarParser.LambdaContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(MxstarParser.ExprContext ctx);
}