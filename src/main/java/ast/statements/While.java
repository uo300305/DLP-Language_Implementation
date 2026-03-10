package ast.statements;
import java.util.List;

import ast.expressions.Expression;
import semantic.Visitor;


public class While extends AbstractStatement {
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

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }}
