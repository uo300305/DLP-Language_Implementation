package ast.common;
import java.util.List;
import ast.definitions.Definition;
import semantic.Visitor;


public class Program implements ASTNode {
    // final (que siempre se me olvida) representa un valor
    // que se convierte en cte tras su primera asignación
    private final List<Definition> definitions;

    public Program(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }


    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }
}
