package ast.expressions;
import ast.definitions.Definition;
import ast.definitions.VarDefinition;
import semantic.Visitor;

public class Variable extends AbstractExpression {
    private final String name;
    private Definition definition;

    public Variable(String name, int line, int column) {
        super(line, column);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Definition getDefinition() { return definition; }
    public void setDefinition(Definition definition) { this.definition = definition; }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
