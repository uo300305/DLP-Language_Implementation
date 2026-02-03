package ast;

public abstract class AbstractExpression extends AbstractLocatable implements Expression {
    protected AbstractExpression(int line, int column) {
        super(line, column);
    }
}
