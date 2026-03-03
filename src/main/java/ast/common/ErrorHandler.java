package ast.common;

import ast.types.ErrorType;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public final class ErrorHandler {

    private static final ErrorHandler INSTANCE = new ErrorHandler();

    private List<ErrorType> errors = new ArrayList<>();

    private ErrorHandler() {

    }

    public static ErrorHandler getInstance() {
        return INSTANCE;
    }

    public void addError(ErrorType error) {
        errors.add(error);
    }

    public void showErrors(PrintStream printStream) {
        errors.forEach(e -> printStream.println(e.toString()));
    }

    public boolean anyError() {
        return !errors.isEmpty();
    }
}
