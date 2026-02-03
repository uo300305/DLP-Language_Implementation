package ast.statements;
import ast.common.AbstractLocatable;
import ast.expressions.Expression;

public class Input extends AbstractLocatable implements Statement {
    private final Expression expression;

    public Input(Expression expression, int line, int column) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
