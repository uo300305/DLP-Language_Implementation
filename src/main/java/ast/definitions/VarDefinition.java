package ast.definitions;
import ast.common.AbstractLocatable;
import ast.statements.Statement;
import ast.types.Type;

public class VarDefinition extends AbstractLocatable implements Definition, Statement {

    private final String name;
    private final Type type;

    public VarDefinition(String name, Type type, int line, int column) {
        super(line, column);
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return type;
    }

}
