package ast;

public class Variable extends AbstractExpression {
    private final String name;

    public Variable(String name, int line, int column) {
        super(line, column);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
