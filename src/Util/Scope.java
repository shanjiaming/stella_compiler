package Util;

import AST.Expr;
import AST.ForStmt;
import AST.LambdaExpr;
import IR.PointerRegister;
import IR.Register;
import Util.MxError.SemanticError;

import java.util.HashMap;

public class  Scope {

    private ForStmt forStmt = null;

    private LambdaExpr lambdaExpr = null;

    private final HashMap<String, Type> namesToTypes = new HashMap<>();
    private final HashMap<String, Register> namesToRegisters = new HashMap<>();

    private final Scope parentScope;
    public HashMap<String, Register> entities = new HashMap<>();

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
        if (namesToTypes.containsKey(name)) {
            throw new SemanticError("Semantic Error: variable redefine", pos);
        }
        if(Type.getClassDef(Type.stringToType(name)) != null)
            throw new SemanticError("same variable and class name", pos);
        namesToTypes.put(name, t);
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
        if (namesToTypes.containsKey(name)) {
            return true;
        } else if (parentScope != null && lookUpon) {
            return parentScope.containsVariable(name, true);
        } else {
            return false;
        }
    }
    public Type getType(String name, boolean lookUpon) {
        if (namesToTypes.containsKey(name)) {
            return namesToTypes.get(name);
        } else if (parentScope != null && lookUpon) {
            return parentScope.getType(name, true);
        }
        return null;
    }
    public Register getRegister(String name, boolean lookUpon) {
        if (namesToRegisters.containsKey(name)) {
            return namesToRegisters.get(name);
        } else if (parentScope != null && lookUpon) {
            return parentScope.getRegister(name, true);
        }
        return null;
    }
//    public void addRegister(Expr expr) {
//        Register register = new Register();
//        expr.entity = register;
//        namesToRegisters.put(register.name, register);
//    }
    public void addPointerRegister(PointerRegister pointerRegister) {
        PointerRegister register = new PointerRegister();
        pointerRegister = register;
        namesToRegisters.put(register.val, register);
    }
    public void addPointerRegister(String name, PointerRegister pointerRegister) {
        namesToRegisters.put(name, pointerRegister);
    }
//    public void addRegister(String name, Expr expr) {
//        Register register = new Register();
//        register.name = name;
//        expr.entity = register;
//        namesToRegisters.put(register.name, register);
//    }
}
