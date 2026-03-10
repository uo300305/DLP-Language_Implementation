package ast.expressions;
import semantic.Visitor;
import ast.types.Type;

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

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
