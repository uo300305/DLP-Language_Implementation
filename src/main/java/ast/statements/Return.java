package ast.statements;
import ast.common.AbstractLocatable;
import ast.expressions.Expression;

public class Return extends AbstractLocatable implements Statement {
    private final Expression expression;

    public Return(Expression expression, int line, int column) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
