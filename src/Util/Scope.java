package Util;

import AST.ForStmt;
import AST.LambdaExpr;
import Util.MxError.SemanticError;

import java.util.HashMap;

public class  Scope {

    private ForStmt forStmt = null;

    private LambdaExpr lambdaExpr = null;

    private final HashMap<String, Type> members = new HashMap<>();

    private final Scope parentScope;

    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
    }

    public Scope(Scope parentScope, ForStmt forStmt) {
        this.parentScope = parentScope;
        this.forStmt = forStmt;
    }

    public Scope(Scope parentScope, LambdaExpr lambdaExpr) {
        this.parentScope = parentScope;
        this.lambdaExpr = lambdaExpr;
    }

    public Scope parentScope() {
        return parentScope;
    }

    public void defineVariable(String name, Type t, position pos) {
        if (members.containsKey(name)) {
            throw new SemanticError("Semantic Error: variable redefine", pos);
        }
        if(Type.getClassDef(Type.stringToType(name)) != null)
            throw new SemanticError("same variable and class name", pos);
        members.put(name, t);
    }

    public ForStmt getForStmt(){
        if (forStmt != null) return forStmt;
        if (parentScope != null) return parentScope.getForStmt();
        return null;
    }

    public LambdaExpr getLambdaExpr(){
        if (lambdaExpr != null) return lambdaExpr;
        if (parentScope != null) return parentScope.getLambdaExpr();
        return null;
    }

    public boolean containsVariable(String name, boolean lookUpon) {
        if (members.containsKey(name)) {
            return true;
        } else if (parentScope != null && lookUpon) {
            return parentScope.containsVariable(name, true);
        } else {
            return false;
        }
    }
    public Type getType(String name, boolean lookUpon) {
        if (members.containsKey(name)) {
            return members.get(name);
        } else if (parentScope != null && lookUpon) {
            return parentScope.getType(name, true);
        }
        return null;
    }
}
