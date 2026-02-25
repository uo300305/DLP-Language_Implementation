package ast.definitions;
import java.util.List;
import ast.common.AbstractLocatable;
import ast.statements.Statement;
import ast.types.FunctionType;
import ast.types.Type;


public class FunctionDefinition extends AbstractLocatable implements Definition {

    private final String name;
    private final FunctionType type;
    private final List<VarDefinition> definitions;
    private final List<Statement> body;

    public FunctionDefinition(
            String name,
            FunctionType type,
            List<VarDefinition> definitions,
            List<Statement> body,
            int line,
            int column
    ) {
        super(line, column);
        this.name = name;
        this.type =type;
        this.definitions = definitions;
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
        return definitions;
    }

    public List<Statement> getBody() {
        return body;
    }
}
