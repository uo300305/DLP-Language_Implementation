package ast.types;

import ast.common.ErrorHandler;
import ast.common.Locatable;
import semantic.Visitor;


public class ErrorType implements Type {
    private String message;
    private Locatable location;

    public ErrorType(String message, Locatable location) {
        this.message = message;
        this.location = location;
        ErrorHandler.getInstance().addError(this);
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
