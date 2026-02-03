package ast;

public class VarDefinition extends AbstractDefinition {

    private final String name;
    private final Type type;

    public VarDefinition(String name, Type type, int line, int column) {
        super(line, column);
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return type;
    }

}
