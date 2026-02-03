package ast;

import java.util.List;

public class FunctionType implements Type {
    private final int dimension;
    private final Type type;
    private final List<VarDefinition> parameters;

    public FunctionType(int dimension, Type type, List<VarDefinition> parameters) {
        this.dimension = dimension;
        this.type = type;
        this.parameters = parameters;
    }

    public int getDimension() {
        return dimension;
    }

    public Type getType() {
        return type;
    }

    public List<VarDefinition> getParameters() {
        return parameters;
    }
}
