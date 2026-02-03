package ast;

public class Logical extends BinaryOperation {
    public Logical(Expression left, Expression right, String operator, int line, int column) {
        super(left, right, operator, line, column);
    }
}
