package ast.expressions;
import ast.common.AbstractLocatable;

public class ArrayAccess extends AbstractLocatable implements Expression {
    private final Expression array;
    private final Expression index;

    public ArrayAccess(Expression array, Expression index, int line, int column) {
        super(line, column);
        this.array = array;
        this.index = index;
    }

    public Expression getArray() {
        return array;
    }

    public Expression getIndex() {
        return index;
    }
}
