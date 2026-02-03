package ast.expressions;
import ast.common.AbstractLocatable;

public abstract class UnaryOperation extends AbstractLocatable implements Expression {
    private final Expression expression;

    protected UnaryOperation(Expression expression, int line, int column) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
