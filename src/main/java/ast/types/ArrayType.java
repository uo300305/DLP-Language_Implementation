package ast.types;

import ast.expressions.Expression;
import semantic.Visitor;

public class ArrayType implements Type {
    private final Expression dimension;
    private final Type elementType;

    public ArrayType(Expression dimension, Type elementType) {
        this.dimension = dimension;
        this.elementType = elementType;
    }

    public Type getElementType() {
        return elementType;
    }

    public Expression getDimension() {
        return dimension;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
