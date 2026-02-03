package ast.statements;
import java.util.List;
import ast.common.AbstractLocatable;
import ast.expressions.Expression;


public class While extends AbstractLocatable implements Statement {
    private final Expression condition;
    private final List<Statement> body;

    public While(Expression condition, List<Statement> body, int line, int column) {
        super(line, column);
        this.condition = condition;
        this.body = body;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getBody() {
        return body;
    }
}
