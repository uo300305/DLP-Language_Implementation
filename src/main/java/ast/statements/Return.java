package ast.statements;
import ast.expressions.Expression;
import semantic.Visitor;

public class Return extends AbstractStatement {
    private final Expression expression;

    public Return(Expression expression, int line, int column) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
