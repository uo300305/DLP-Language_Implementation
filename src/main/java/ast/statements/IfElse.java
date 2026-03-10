package ast.statements;
import java.util.List;

import ast.expressions.Expression;
import semantic.Visitor;


public class IfElse extends AbstractStatement {
    // Lista de expresiones, condiciones: Primera if, segunda else
    private final Expression condition;
    private final List<Statement> ifBody;
    private final List<Statement> elseBody;

    public IfElse(Expression condition, List<Statement> ifBody, List<Statement> elseBody, int line, int column) {
        super(line, column);
        this.condition = condition;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getIfBody() {
        return ifBody;
    }

    public List<Statement> getElseBody() {
        return elseBody;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }

}
