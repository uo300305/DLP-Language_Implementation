package ast.statements;
import ast.common.AbstractLocatable;
import ast.expressions.Expression;

public class Log extends AbstractLocatable implements Statement {
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
}
