package ast.types;
import java.util.List;
import ast.definitions.VarDefinition;
import semantic.Visitor;


public class FunctionType implements Type {
    private final Type returnType;
    private final List<VarDefinition> parameters;

    public FunctionType(Type type, List<VarDefinition> parameters) {
        this.returnType = type;
        this.parameters = parameters;
    }
    
    public Type getType() {
        return returnType;
    }

    public List<VarDefinition> getParameters() {
        return parameters;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
