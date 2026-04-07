package ast.definitions;
import semantic.Visitor;
import ast.statements.Statement;
import ast.types.Type;

public class VarDefinition extends AbstractDefinition implements Statement {

    private int offset;

    public VarDefinition(String name, Type type, int line, int column) {
        super(line, column, name, type);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
