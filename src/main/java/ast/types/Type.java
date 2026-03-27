package ast.types;
import ast.common.ASTNode;
import ast.common.Locatable;

import java.util.List;

public interface Type extends ASTNode {
    // Los métodos mustTo son aquellos que solo comprueban
    // Los otros comparan y tipan
    void mustBeLogical(Locatable locatable);
    Type arithmetic(Type other, Locatable locatable);
    Type arithmetic(Locatable locatable);
    Type comparison(Type other, Locatable locatable);
    Type logical(Type other, Locatable locatable);
    Type logical(Locatable locatable);
    void mustPromote(Type other, Locatable locatable);
    void mustBeBuiltIn(Locatable locatable);
    Type squareBrackets(Type other, Locatable locatable);
    Type dot(String name, Locatable locatable);
    Type parenthesis(List<Type> types, Locatable locatable);
}