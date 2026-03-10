package ast.expressions;

import semantic.Visitor;

public class Arithmetic extends BinaryOperation {
    public Arithmetic(Expression left, Expression right, String operator, int line, int column) {
        super(left, right, operator, line, column);
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
