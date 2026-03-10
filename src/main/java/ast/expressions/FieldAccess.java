package ast.expressions;
import semantic.Visitor;

public class FieldAccess extends AbstractExpression {
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

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
