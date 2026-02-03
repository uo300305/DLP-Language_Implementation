package ast.types;

public final class IntType implements Type {
    private static final IntType INSTANCE = new IntType();

    private IntType() {
    }

    public static IntType getInstance() {
        return INSTANCE;
    }
}
