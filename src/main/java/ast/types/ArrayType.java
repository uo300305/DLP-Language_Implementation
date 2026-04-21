package ast.types;

import ast.common.Locatable;
import semantic.Visitor;

public class ArrayType extends AbstractType {
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
    public Type squareBrackets(Type other, Locatable locatable) {
        if(other.equals(IntType.getInstance()) || other.equals(CharType.getInstance()))
            return this.elementType;
        else return new ErrorType("El operador '[]' debe contener un tipo int", locatable);
    }

    @Override
    public int getNumberOfBytes() {
        return getElementType().getNumberOfBytes()*getDimension();
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }

    @Override
    public String toString() {
        return "ArrayType[of:" + elementType + ",size:" + dimension + "]";
    }

}
