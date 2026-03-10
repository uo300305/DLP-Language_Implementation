package ast.expressions;

import semantic.Visitor;

public class UnaryNot extends UnaryOperation {
    public UnaryNot(Expression expression, int line, int column) {
        super(expression, line, column);
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
