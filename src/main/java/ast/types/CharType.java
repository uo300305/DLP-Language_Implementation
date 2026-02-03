package ast.types;

public final class CharType implements Type {
    private static final CharType INSTANCE = new CharType();

    private CharType() {
    }

    public static CharType getInstance() {
        return INSTANCE;
    }
}
