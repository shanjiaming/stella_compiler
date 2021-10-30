package Util;

import AST.ClassDef;
import AST.FuncDef;
import Parser.MxParser;
import Util.MxError.MxError;
import Util.MxError.SemanticError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Type {
    public String typeName;
    public int dim;

    public Type(String typeName, int dim) {
        this.typeName = typeName;
        this.dim = dim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return dim == type.dim && Objects.equals(typeName, type.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName, dim);
    }

    public static Type visit(MxParser.TypeContext ctx) {
        return new Type(ctx.baseType().getText(), ctx.LeftBracket().size());
    }

    public static Type visit(MxParser.BaseTypeContext ctx) {
        return new Type(ctx.getText(), 0);
    }

    public static Type stringToType(String str) {
        return new Type(str, 0);
    }

    public static Type reduceDim(Type type) {
        return new Type(type.typeName, type.dim-1);
    }

    public static final Type INT_TYPE = new Type("int", 0);
    public static final Type BOOL_TYPE = new Type("bool", 0);
    public static final Type STRING_TYPE = new Type("string", 0);

    private static final HashSet<Type> basicTypes = new HashSet<>(Arrays.asList(INT_TYPE, BOOL_TYPE, STRING_TYPE));
    private static final HashMap<Type, ClassDef> classTypes = new HashMap<>();
    private static final HashMap<Type, FuncDef> funcTypes = new HashMap<>();

    public static void addClassType(ClassDef classDef, position pos){
        Type classType = stringToType(classDef.name);
        if (hasClassTypes(classType) | hasFuncTypes(classType)) throw new SemanticError("add class type already exists", pos);
        classTypes.put(classType, classDef);
    }
    public static void addFuncType(FuncDef funcDef, position pos){
        Type funcType = stringToType(funcDef.name);
        if (hasClassTypes(funcType) | hasFuncTypes(funcType)) throw new SemanticError("add func type already exists", pos);
        funcTypes.put(funcType, funcDef);
    }

    public static boolean hasBasicTypes(Type type){
        return basicTypes.contains(type);
    }
    public static boolean hasClassTypes(Type type){
        return classTypes.containsKey(type);
    }
    public static boolean hasFuncTypes(Type type){
        return funcTypes.containsKey(type);
    }
    public static boolean hasTypes(Type type){
        return hasBasicTypes(type) || hasClassTypes(type);
    }

}
