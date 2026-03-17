package ast.definitions;

import ast.common.AbstractLocatable;
import ast.types.Type;
import semantic.Visitor;

public abstract class AbstractDefinition extends AbstractLocatable implements Definition {

    private int scope;
    private final String name;
    private final Type type;

    public AbstractDefinition(int line, int column, String name, Type type) {
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

    @Override
    public int getScope() {
        return scope;
    }

    @Override
    public void setScope(int scope) {
        this.scope = scope;
    }
}
