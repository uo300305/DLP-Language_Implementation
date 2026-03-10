package ast.expressions;

public abstract class UnaryOperation extends AbstractExpression {
    private final Expression expression;

    public Expression getExpression(){
        return expression;
    }

    protected UnaryOperation(Expression expression, int line, int column) {
        super(line, column);
        this.expression = expression;
    }
}
