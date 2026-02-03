package ast;

public class UnaryNot extends UnaryOperation {
    public UnaryNot(Expression expression, int line, int column) {
        super(expression, line, column);
    }
}
