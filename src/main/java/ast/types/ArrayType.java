package ast.types;

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
}
