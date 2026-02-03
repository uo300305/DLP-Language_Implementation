package ast.statements;
import ast.common.AbstractLocatable;
import ast.expressions.Expression;

public class Assignment extends AbstractLocatable implements Statement {
    private final Expression left;
    private final Expression right;

    public Assignment(Expression left, Expression right, int line, int column) {
        super(line, column);
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
