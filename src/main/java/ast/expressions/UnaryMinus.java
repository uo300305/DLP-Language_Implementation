package ast.expressions;

import semantic.Visitor;

public class UnaryMinus extends UnaryOperation {
    public UnaryMinus(Expression expression, int line, int column) {
        super(expression, line, column);
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
