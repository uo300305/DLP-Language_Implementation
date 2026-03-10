package ast.common;

import semantic.Visitor;

public interface ASTNode {
    <PT, RT> RT accept(Visitor<PT, RT> visitor, PT paramType);
}
