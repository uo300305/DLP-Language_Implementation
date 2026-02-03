package ast.expressions;

public class NumberLiteral extends Literal {
    private final double value;

    public NumberLiteral(double value, int line, int column) {
        super(line, column);
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
