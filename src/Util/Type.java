package Util;

import AST.ClassDef;
import AST.FuncDef;
import Parser.MxParser;
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
        if ("null".equals(type.typeName)) {
            if ((typeName == "int" || typeName == "bool") && dim == 0) return false;
            return true;
        }
        if ("null".equals(typeName)) {
            if ((type.typeName == "int" || type.typeName == "bool") && type.dim == 0) return false;
            return true;
        }
        return dim == type.dim && Objects.equals(typeName, type.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName, dim);
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeName='" + typeName + '\'' +
                ", dim=" + dim +
                '}';
    }

    public static boolean isSameType(Type t1, Type t2){
        return (t1 != null) && t1.equals(t2);
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
        return new Type(type.typeName, type.dim - 1);
    }

    public static final Type INT_TYPE = new Type("int", 0);
    public static final Type BOOL_TYPE = new Type("bool", 0);
    public static final Type STRING_TYPE = new Type("string", 0);

    private static final HashSet<Type> basicTypes = new HashSet<>(Arrays.asList(INT_TYPE, BOOL_TYPE, STRING_TYPE));
    private static final HashMap<Type, ClassDef> classTypes = new HashMap<>();
    private static final HashMap<String, FuncDef> funcTypes = new HashMap<>();

    public static void addClassType(ClassDef classDef, position pos) {
        Type classType = stringToType(classDef.name);
        if (hasClassType(classType) | hasFuncType(classType.typeName))
            throw new SemanticError("add class type already exists", pos);
        classTypes.put(classType, classDef);
    }

    public static void addFuncType(FuncDef funcDef, position pos) {
        if (hasClassType(stringToType(funcDef.name)) | hasFuncType(funcDef.name))
            throw new SemanticError("add func type already exists", pos);
        funcTypes.put(funcDef.name, funcDef);
    }

    public static boolean hasBasicType(Type type) {
        return basicTypes.contains(type);
    }

    public static boolean hasClassType(Type type) {
        return classTypes.containsKey(type);
    }

    public static boolean hasFuncType(String type) {
        return funcTypes.containsKey(type);
    }

    public static boolean hasType(Type type) {
        return hasBasicType(type) || hasClassType(type);
    }

    public static ClassDef getClassDef(Type type) {
        return classTypes.get(type);
    }

    public static FuncDef getFuncDef(Type type) {
        return funcTypes.get(type);
    }


}
