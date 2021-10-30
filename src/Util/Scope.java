package Util;

import AST.ForStmt;
import Util.MxError.SemanticError;

import java.util.HashMap;

public class  Scope {

    private ForStmt forStmt = null;

    private final HashMap<String, Type> members = new HashMap<>();

    private final Scope parentScope;

    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
    }

    public Scope(Scope parentScope, ForStmt forStmt) {
        this.parentScope = parentScope;
        this.forStmt = forStmt;
    }

    public Scope parentScope() {
        return parentScope;
    }

    public void defineVariable(String name, Type t, position pos) {
        if (members.containsKey(name)) {
            throw new SemanticError("Semantic Error: variable redefine", pos);
        }
        members.put(name, t);
    }

    public ForStmt getForStmt(){
        if (forStmt != null) return forStmt;
        if (parentScope != null) return parentScope.getForStmt();
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
