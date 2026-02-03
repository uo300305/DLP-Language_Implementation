package ast;

public class Cast extends AbstractExpression {
    private final Type targetType;
    private final Expression expression;

    public Cast(Type targetType, Expression expression, int line, int column) {
        super(line, column);
        this.targetType = targetType;
        this.expression = expression;
    }

    public Type getTargetType() {
        return targetType;
    }

    public Expression getExpression() {
        return expression;
    }
}
