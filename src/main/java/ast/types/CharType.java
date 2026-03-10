package ast.types;

import semantic.Visitor;

public final class CharType implements Type {
    private static final CharType INSTANCE = new CharType();

    private CharType() {
    }

    public static CharType getInstance() {
        return INSTANCE;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(getInstance(), param);
    }
}
