package ast.types;

import ast.common.Locatable;
import semantic.Visitor;

public final class NumberType extends AbstractType {
    private static final NumberType INSTANCE = new NumberType();

    private NumberType() {
    }

    @Override
    public Type arithmetic(Type other, Locatable locatable) {
        if(other.equals(IntType.getInstance()) ||
                other.equals(CharType.getInstance()) ||
                other.equals(NumberType.getInstance())){
            return this;
        }
        else {
            return new ErrorType("El tipo " + other + " no es valido para expresiones aritmeticas", locatable);
        }
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return this;
    }

    @Override
    public Type comparison(Type other, Locatable locatable) {
        if(other.equals(IntType.getInstance()) ||
                other.equals(CharType.getInstance()) ||
                other.equals(NumberType.getInstance())){
            return IntType.getInstance();
        }
        else {
            return new ErrorType("El tipo " + other + " no es valido para expresiones de comparación", locatable);
        }
    }

    @Override
    public void mustPromote(Type other, Locatable locatable) {
        if(other.equals(NumberType.getInstance()) || other instanceof ErrorType) {
            return;
        }
        else
            new ErrorType("El tipo " + this + " no puede promocionar a " + other, locatable);
    }

    public void mustBeBuiltIn(Locatable locatable) {
        // Vacío
    }

    public static NumberType getInstance() {
        return INSTANCE;
    }

    @Override
    public int getNumberOfBytes() {
        return 4;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(getInstance(), param);
    }

    @Override
    public String toString() {
        return "number";
    }
}
