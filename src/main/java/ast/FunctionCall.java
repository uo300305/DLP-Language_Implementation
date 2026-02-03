package ast;

import java.util.List;

public class FunctionCall extends AbstractExpression {
    private final String name;
    private final List<Expression> arguments;

    public FunctionCall(String name, List<Expression> arguments, int line, int column) {
        super(line, column);
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public List<Expression> getArguments() {
        return arguments;
    }
}
