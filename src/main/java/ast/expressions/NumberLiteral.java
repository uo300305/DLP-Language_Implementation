package ast.expressions;

import semantic.Visitor;

public class NumberLiteral extends Literal {
    private final double value;

    public NumberLiteral(double value, int line, int column) {
        super(line, column);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
