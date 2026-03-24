package ast.definitions;
import java.util.List;

import semantic.Visitor;
import ast.statements.Statement;
import ast.types.FunctionType;


public class FunctionDefinition extends AbstractDefinition implements Definition {

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
        super(line, column, name, type);
        this.definitions = definitions;
        this.body = body;
    }

    public List<VarDefinition> getDefinitions() {
        return definitions;
    }

    public List<Statement> getBody() {
        return body;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
