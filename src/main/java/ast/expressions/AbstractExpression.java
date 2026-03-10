package ast.expressions;

import ast.common.AbstractLocatable;

public abstract class AbstractExpression extends AbstractLocatable implements Expression  {
    private boolean lValue;

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
}
