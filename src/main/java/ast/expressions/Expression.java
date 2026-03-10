package ast.expressions;
import ast.common.Locatable;

public interface Expression extends Locatable {
    boolean islValue();
    void setlValue(boolean lValue);
}
