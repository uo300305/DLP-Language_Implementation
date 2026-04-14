package ast.definitions;
import ast.common.Locatable;
import ast.types.Type;

public interface Definition extends Locatable {

    String getName();

    Type getType();

    int getScope();
    void setScope(int scope);


}
