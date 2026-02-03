package ast.expressions;

public class Arithmetic extends BinaryOperation {
    public Arithmetic(Expression left, Expression right, String operator, int line, int column) {
        super(left, right, operator, line, column);
    }
}
