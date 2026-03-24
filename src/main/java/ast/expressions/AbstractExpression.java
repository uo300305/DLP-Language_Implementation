package ast.expressions;

import ast.common.AbstractLocatable;
import ast.types.Type;

public abstract class AbstractExpression extends AbstractLocatable implements Expression  {
    private boolean lValue;
    private Type type;

    public AbstractExpression(int line, int column) {
        super(line, column);
    }

    @Override
    public boolean islValue() {
        return lValue;
    }

    @Override
    public void setlValue(boolean lValue) {
        this.lValue = lValue;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }
}
