package Util;

import Parser.MxParser;

import java.util.Objects;

public class Type{
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

    public static Type visit(MxParser.TypeContext ctx){
        return new Type(ctx.baseType().getText(), ctx.LeftBracket().size());
    }

    public static Type visit(MxParser.BaseTypeContext ctx){
        return new Type(ctx.getText(), 0);
    }

    public static Type StringToType(String str){
        return new Type(str,0);
    }

    public static Type INTTYPE = new Type("int",0);
    public static Type BOOLTYPE = new Type("bool",0);
    public static Type STRINGTYPE = new Type("string",0);
}
