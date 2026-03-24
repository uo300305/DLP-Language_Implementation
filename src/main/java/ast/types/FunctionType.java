package ast.types;
import java.util.List;

import ast.common.Locatable;
import ast.definitions.VarDefinition;
import semantic.Visitor;


public class FunctionType extends AbstractType {
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
    public Type parenthesis(List<Type> types, Locatable locatable) {
        if (types.size() != this.parameters.size()) {
            return new ErrorType("Se esperaban " + this.parameters.size() + " argumentos pero se recibieron " + types.size(), locatable);
        }
        for (int i = 0; i < types.size(); i++) {
            types.get(i).mustPromote(this.parameters.get(i).getType(), locatable);
        }
        return this.returnType;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }

    @Override
    public String toString() {
        return "function: " +  returnType;
    }
}
