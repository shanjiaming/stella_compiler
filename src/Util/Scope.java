package Util;

//import MIR.register;
import Util.MxError.SemanticError;

import java.util.HashMap;

public class  Scope {

    private final HashMap<String, Type> members = new HashMap<>();
//    public HashMap<String, register> entities = new HashMap<>();
    private final Scope parentScope;


    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
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
    /*public register getEntity(String name, boolean lookUpon) {
        if (entities.containsKey(name)) {
            return entities.get(name);
        } else if (parentScope != null && lookUpon) {
            return parentScope.getEntity(name, true);
        }
        return null;
    }*/
}
