package ast;

public abstract class AbstractDefinition extends AbstractLocatable implements Definition {
    protected AbstractDefinition(int line, int column) {
        super(line, column);
    }
}
