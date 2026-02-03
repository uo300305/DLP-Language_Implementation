package ast;

import java.util.List;

public class FunctionDefinition extends AbstractDefinition {

    private final String name;
    private final FunctionType type;
    private final List<VarDefinition> parameters;
    private final List<Statement> body;

    public FunctionDefinition(
            String name,
            FunctionType type,
            List<VarDefinition> parameters,
            List<Statement> body,
            int line,
            int column
    ) {
        super(line, column);
        this.name = name;
        this.type = type;
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return type;
    }

    public List<VarDefinition> getParameters() {
        return parameters;
    }

    public List<Statement> getBody() {
        return body;
    }
}
