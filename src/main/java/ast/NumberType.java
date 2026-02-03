package ast;

public final class NumberType implements Type {
    private static final NumberType INSTANCE = new NumberType();

    private NumberType() {
    }

    public static NumberType getInstance() {
        return INSTANCE;
    }
}
