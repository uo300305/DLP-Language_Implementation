package ast.definitions;
import ast.common.Locatable;
import ast.types.Type;

public interface Definition extends Locatable {

    public String getName();

    public Type getType();
}
