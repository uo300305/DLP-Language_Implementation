package ast.statements;
import java.util.List;
import ast.common.AbstractLocatable;
import ast.expressions.Expression;


public class IfElse extends AbstractLocatable implements Statement {
    // Lista de expresiones, condiciones: Primera if, segunda else
    private final List<Expression> conditions;
    private final List<Statement> ifBody;
    private final List<Statement> elseBody;

    public IfElse(List<Expression> conditions, List<Statement> ifBody, List<Statement> elseBody, int line, int column) {
        super(line, column);
        this.conditions = conditions;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }

    public List<Expression> getCondition() {
        return conditions;
    }

    public List<Statement> getIfBody() {
        return ifBody;
    }

    public List<Statement> getElseBody() {
        return elseBody;
    }
}
