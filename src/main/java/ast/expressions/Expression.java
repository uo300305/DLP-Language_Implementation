package ast.expressions;
import ast.common.Locatable;
import ast.types.Type;

public interface Expression extends Locatable {
    boolean islValue();
    void setlValue(boolean lValue);
    Type getType();
    void setType(Type type);
}
