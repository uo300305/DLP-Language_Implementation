package ast.types;

import semantic.Visitor;

public final class IntType implements Type {
    private static final IntType INSTANCE = new IntType();

    private IntType() {
    }

    public static IntType getInstance() {
        return INSTANCE;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(getInstance(), param);
    }
}
