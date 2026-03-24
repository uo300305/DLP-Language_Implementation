package ast.types;
import ast.common.ASTNode;
import ast.common.AbstractLocatable;
import semantic.Visitor;

public class RecordField extends AbstractLocatable {
    private final String name;
    private final Type type;

    public RecordField(int line, int column, String name, Type type) {
        super(line, column);
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }

    @Override
    public String toString() {
        return name + ": " + type;
    }
}
