package ast;

public class Comparison extends BinaryOperation {
    public Comparison(Expression left, Expression right, String operator, int line, int column) {
        super(left, right, operator, line, column);
    }
}
