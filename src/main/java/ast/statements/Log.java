package ast.statements;
import ast.expressions.Expression;
import semantic.Visitor;

public class Log extends AbstractStatement {
    // Una unica expresión. Un conjunto de expresiones se puede resumir en una única expresión
    // Lo cual nos simplifica la vida, pudiendo acceder sin while
    private final Expression output;

    public Log(Expression output, int line, int column) {
        super(line, column);
        this.output = output;
    }

    public Expression getExpression() {
        return output;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
