package ast.definitions;
import java.util.List;
import ast.common.AbstractLocatable;
import ast.statements.Statement;
import ast.types.FunctionType;
import ast.types.Type;


public class FunctionDefinition extends AbstractLocatable implements Definition {

    private final String name;
    private final FunctionType type;
    private final List<VarDefinition> parameters;
    private final List<Statement> body;

    public FunctionDefinition(
            String name,
            Type returnType,
            List<VarDefinition> parameters,
            List<Statement> body,
            int line,
            int column
    ) {
        super(line, column);
        this.name = name;
        // Entiendo que cuando se define una función se indica el return
        // y con ello se pasa el tipo de retorno
        this.type = new FunctionType(returnType, parameters);
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
