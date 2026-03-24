package ast.types;

import ast.common.ErrorHandler;
import ast.common.Locatable;
import semantic.Visitor;

import java.util.List;


public class ErrorType extends AbstractType {
    private String message;
    private Locatable location;

    public ErrorType(String message, Locatable location) {
        this.message = message;
        this.location = location;
        ErrorHandler.getInstance().addError(this);
    }

    @Override
    public void mustBeLogical(Locatable locatable) { }

    @Override
    public void mustPromote(Type other, Locatable locatable) { }

    @Override
    public void mustBeBintIn(Locatable locatable) { }

    @Override
    public Type arithmetic(Type other, Locatable locatable) { return this; }

    @Override
    public Type arithmetic(Locatable locatable) { return this; }

    @Override
    public Type comparison(Type other, Locatable locatable) { return this; }

    @Override
    public Type logical(Type other, Locatable locatable) { return this; }

    @Override
    public Type logical(Locatable locatable) { return this; }

    @Override
    public Type squareBrackets(Type other, Locatable locatable) { return this; }

    @Override
    public Type dot(String name, Locatable locatable) { return this; }

    @Override
    public Type parenthesis(List<Type> types, Locatable locatable) { return this; }

    @Override
    public void mustBePrimitive(Locatable locatable) {

    }

    @Override
    public String toString() {
        return "Location: (" + location.getLine() + ":" + location.getColumn() + ")" + " " + message;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
