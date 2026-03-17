package ast.definitions;
import ast.common.AbstractLocatable;
import semantic.Visitor;
import ast.statements.Statement;
import ast.types.Type;

public class VarDefinition extends AbstractDefinition implements Statement {


    public VarDefinition(String name, Type type, int line, int column) {
        super(line, column, name, type);
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
