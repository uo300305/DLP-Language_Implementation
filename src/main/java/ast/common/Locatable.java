package ast.common;

public interface Locatable extends ASTNode{
    public int getLine();
    public int getColumn();
}
