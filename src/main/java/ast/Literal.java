package ast;

public abstract class Literal extends AbstractExpression {
    protected Literal(int line, int column) {
        super(line, column);
    }
}
