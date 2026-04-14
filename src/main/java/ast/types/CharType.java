package ast.types;

import ast.common.Locatable;
import semantic.Visitor;

public final class CharType extends AbstractType {
    private static final CharType INSTANCE = new CharType();

    private CharType() {
    }

    @Override
    public void mustBeLogical(Locatable locatable) {
        // El cuerpo queda vacío, pues no es necesario lanzar error. Es un tipo lógico.
    }

    @Override
    public Type arithmetic(Type other, Locatable locatable) {
        if(other.equals(IntType.getInstance()) || other.equals(CharType.getInstance())){
            return IntType.getInstance();
        }

        else if(other.equals(NumberType.getInstance())) {
            return other;
        }

        else {
            return new ErrorType("El tipo " + other + " no es valido para expresiones aritmeticas", locatable);
        }
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return IntType.getInstance();
    }

    @Override
    public Type comparison(Type other, Locatable locatable) {
        if(other.equals(IntType.getInstance()) || other.equals(CharType.getInstance()) || other.equals(NumberType.getInstance())){
            return IntType.getInstance();
        }
        else {
            return new ErrorType("El tipo " + other + " no es valido para expresiones de comparación", locatable);
        }
    }

    @Override
    public Type logical(Type other, Locatable locatable) {
        if(other.equals(IntType.getInstance()) || other.equals(CharType.getInstance()))
            return this;

        else
            return new ErrorType("El tipo " + other + " no es valido para expresiones lógicas", locatable);
    }

    @Override
    public Type logical(Locatable locatable) {
        return IntType.getInstance();
    }

    @Override
    public void mustPromote(Type other, Locatable locatable) {
        if(other.equals(IntType.getInstance()) ||
                other.equals(CharType.getInstance()) ||
                other.equals(NumberType.getInstance()) ||
                other instanceof ErrorType) {
            return;
        }
        else
            new ErrorType("El tipo " + this + " no puede promocionar a " + other, locatable);
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
        // Vacío
    }

    @Override
    public int getNumberOfBytes() {
        return 1;
    }

    public static CharType getInstance() {
        return INSTANCE;
    }

    @Override
    public char suffix() {
        return 'b';
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(getInstance(), param);
    }

    @Override
    public String toString() {
        return "char";
    }
}
