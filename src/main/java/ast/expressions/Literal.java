package ast.expressions;

import ast.common.AbstractLocatable;

public abstract class Literal extends AbstractExpression {
    protected Literal(int line, int column) {
        super(line, column);
    }
}
