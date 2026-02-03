package ast;

import java.util.List;

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
}
