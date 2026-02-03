package ast.expressions;
import java.util.List;
import ast.common.AbstractLocatable;
import ast.statements.Statement;


// Como hereda de ambos no puedo hacer que herede de las implementaciones abstractas
// Por lo que añadimos locatable a mano
public class FunctionCall extends AbstractLocatable implements Statement, Expression {
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
}
