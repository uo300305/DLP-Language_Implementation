package ast.types;

import semantic.Visitor;

public final class NumberType implements Type {
    private static final NumberType INSTANCE = new NumberType();

    private NumberType() {
    }

    public static NumberType getInstance() {
        return INSTANCE;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(getInstance(), param);
    }
}
