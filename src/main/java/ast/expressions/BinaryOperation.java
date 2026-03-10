package ast.expressions;
import ast.common.AbstractLocatable;

public abstract class BinaryOperation extends AbstractExpression {
    private final Expression left;
    private final Expression right;
    private final String operator;

    protected BinaryOperation(Expression left, Expression right, String operator, int line, int column) {
        super(line, column);
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public String getOperator() {
        return operator;
    }
}
