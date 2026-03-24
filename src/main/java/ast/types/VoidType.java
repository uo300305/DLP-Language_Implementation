package ast.types;

import semantic.Visitor;

public final class VoidType extends AbstractType {
    private static final VoidType INSTANCE = new VoidType();

    private VoidType() {
    }

    public static VoidType getInstance() {
        return INSTANCE;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(getInstance(), param);
    }

    @Override
    public String toString() {
        return "void";
    }
}
