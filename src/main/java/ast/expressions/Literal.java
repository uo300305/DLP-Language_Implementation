package ast.expressions;

import ast.common.AbstractLocatable;

public abstract class Literal extends AbstractLocatable implements Expression {
    protected Literal(int line, int column) {
        super(line, column);
    }
}
