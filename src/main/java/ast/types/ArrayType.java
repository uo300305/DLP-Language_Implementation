package ast.types;

import semantic.Visitor;

public class ArrayType implements Type {
    private final int dimension;
    private final Type elementType;

    public ArrayType(int dimension, Type elementType) {
        this.dimension = dimension;
        this.elementType = elementType;
    }

    public Type getElementType() {
        return elementType;
    }

    public int getDimension() {
        return dimension;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
