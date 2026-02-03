package ast.types;

public class ArrayType implements Type {
    private final Type elementType;
    private final int dimension;

    public ArrayType(Type elementType, int dimension) {
        this.elementType = elementType;
        this.dimension = dimension;
    }

    public Type getElementType() {
        return elementType;
    }

    public int getDimension() {
        return dimension;
    }
}
