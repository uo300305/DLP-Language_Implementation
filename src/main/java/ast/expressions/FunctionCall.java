package ast.expressions;
import java.util.List;

import semantic.Visitor;
import ast.statements.Statement;


// Como hereda de ambos no puedo hacer que herede de las implementaciones abstractas
// Por lo que añadimos locatable a mano
public class FunctionCall extends AbstractExpression implements Statement {
    private final Variable name;
    private final List<Expression> arguments;

    public FunctionCall(Variable name, List<Expression> arguments, int line, int column) {
        super(line, column);
        this.name = name;
        this.arguments = arguments;
    }

    public Variable getName() {
        return name;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
