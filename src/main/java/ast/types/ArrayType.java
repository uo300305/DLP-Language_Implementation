package ast.types;

import ast.common.Locatable;
import ast.expressions.Expression;
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
        // Comprobamos que sea un int o pueda promocionar a este
        other.mustBeBintIn(locatable);
        // devolvemos su tipo
        return this.elementType;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }

    @Override
    public String toString() {
        return elementType + "[" + dimension + "]";
    }

}
