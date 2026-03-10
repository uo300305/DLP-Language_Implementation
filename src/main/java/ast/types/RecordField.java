package ast.types;
import ast.common.ASTNode;
import semantic.Visitor;

public class RecordField implements ASTNode {
    private final String name;
    private final Type type;

    public RecordField(String name, Type type) {
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
}
