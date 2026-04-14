package ast.types;

import ast.common.Locatable;

import java.util.List;

public abstract class AbstractType implements Type {

    @Override
    public char suffix() {
        throw new RuntimeException("El tipo " + this + " no soporta esta operación");
    }

    @Override
    public void mustBeLogical(Locatable locatable) {
        new ErrorType("Se esperaba un típo lógico", locatable);
    }

    @Override
    public Type arithmetic(Type other, Locatable locatable) {
        return new ErrorType("El tipo " + this + " no es valido para expresiones aritmeticas", locatable);
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        // Recordemos que this hace referencia al contexto por lo que llamará al tipo que llame a esta función
        return new ErrorType("El tipo " + this + " no es valido para expresiones aritmeticas unarias", locatable);
    }

    @Override
    public Type comparison(Type other, Locatable locatable) {
        return new ErrorType("El tipo " + this + " no es valido para expresiones de comparación", locatable);
    }

    @Override
    public Type logical(Type other, Locatable locatable) {
        return new ErrorType("El tipo " + this + " no es valido para expresiones lógicas", locatable);
    }

    @Override
    public Type logical(Locatable locatable) {
        return new ErrorType("El tipo " + this + " no es valido para expresiones lógicas unarias", locatable);
    }

    @Override
    public void mustPromote(Type other, Locatable locatable) {
        if (other instanceof ErrorType) return;
        new ErrorType("El tipo " + this + " no puede promocionar a " + other, locatable);
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
        new ErrorType("El tipo debe ser primitivo", locatable);
    }

    @Override
    public Type squareBrackets(Type other, Locatable locatable) {
        return new ErrorType("No puede usarse '[]' para acceder a un tipo " + this, locatable);
    }

    @Override
    public Type dot(String name, Locatable locatable) {
        return new ErrorType("No puede usarse '.' para acceder a un campo del tipo " + this, locatable);
    }

    @Override
    public Type parenthesis(List<Type> types, Locatable locatable) {
        return new ErrorType("No puede usarse '()' para pasarle parámetros a un tipo " + this, locatable);
    }

    @Override
    public int getNumberOfBytes() {
        // TODO es este error?
        throw new RuntimeException("No se soporta esta operación");
    }
}
