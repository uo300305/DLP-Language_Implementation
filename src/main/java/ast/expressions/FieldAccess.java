package ast.expressions;
import ast.common.AbstractLocatable;

public class FieldAccess extends AbstractLocatable implements Expression {
    private final Expression expression;
    private final String field;

    public FieldAccess(Expression expression, String field, int line, int column) {
        super(line, column);
        this.expression = expression;
        this.field = field;
    }

    public Expression getExpression() {
        return expression;
    }

    public String getField() {
        return field;
    }
}
