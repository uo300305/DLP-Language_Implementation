package semantic;

import ast.definitions.FunctionDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

public class TypeCheckingVisitor extends AbstractVisitor<Type, Boolean> {
    @Override
    public Boolean visit(FunctionDefinition functionDefinition, Type param) {
        functionDefinition.getType().accept(this, param);

        functionDefinition.getDefinitions().forEach(d -> d.accept(this, param));

        // Guardamos el tipo de retorno
        Type returnType = ((FunctionType)functionDefinition.getType()).getType();

        // Pasamos como param el tipo de retorno de la función
        functionDefinition.getBody().forEach(s -> s.accept(this, returnType));

        return true;
    }

    @Override
    public Boolean visit(IntLiteral intLiteral, Type param) {
        intLiteral.setType(IntType.getInstance());
        return true;
    }

    @Override
    public Boolean visit(NumberLiteral numberLiteral, Type param) {
        numberLiteral.setType(NumberType.getInstance());
        return true;
    }

    @Override
    public Boolean visit(CharLiteral charLiteral, Type param) {
        charLiteral.setType(CharType.getInstance());
        return true;
    }

    @Override
    public Boolean visit(Variable var, Type param) {
        var.setType(var.getDefinition().getType());
        return true;
    }

    @Override
    public Boolean visit(IfElse aIf, Type param) {
        aIf.getCondition().accept(this, param);

        aIf.getCondition().getType().mustBeLogical(aIf.getCondition());

        aIf.getIfBody().forEach(s -> s.accept(this, param));
        aIf.getElseBody().forEach(s -> s.accept(this, param));

        return true;
    }

    @Override
    public Boolean visit(While aWhile, Type param) {
        aWhile.getCondition().accept(this, param);

        aWhile.getCondition().getType().mustBeLogical(aWhile.getCondition());

        aWhile.getBody().forEach(s -> s.accept(this, param));

        return true;
    }

    @Override
    public Boolean visit(Arithmetic arithmetic, Type param) {
        super.visit(arithmetic, param);

        arithmetic.setType(arithmetic.getLeft().getType().arithmetic(arithmetic.getRight().getType(), arithmetic));
        return true;
    }

    @Override
    public Boolean visit(Logical logical, Type param) {
        super.visit(logical, param);

        logical.setType(logical.getLeft().getType().logical(logical.getRight().getType(), logical));
        return true;
    }

    @Override
    public Boolean visit(Comparison comparison, Type param) {
        super.visit(comparison, param);

        comparison.setType(comparison.getLeft().getType().comparison(comparison.getRight().getType(), comparison));
        return true;
    }

    @Override
    public Boolean visit(UnaryMinus unaryMinus, Type param) {
        super.visit(unaryMinus, param);

        unaryMinus.setType(unaryMinus.getExpression().getType().arithmetic(unaryMinus));
        return true;
    }

    @Override
    public Boolean visit(UnaryNot unaryNot, Type param) {
        super.visit(unaryNot, param);

        unaryNot.setType(unaryNot.getExpression().getType().logical(unaryNot));
        return true;
    }

    @Override
    public Boolean visit(Assignment assignment, Type param) {
        super.visit(assignment, param);
        assignment.getRight().getType().mustPromote(assignment.getLeft().getType(), assignment);
        return true;
    }

    @Override
    public Boolean visit(Return aReturn, Type param) {
        super.visit(aReturn, param);

        // El tipo debe coincidir con el de return de function
        aReturn.getExpression().getType().mustPromote(param, aReturn);
        return true;
    }

    @Override
    public Boolean visit(Log log, Type param) {
        log.getExpression().accept(this, param);

        log.getExpression().getType().mustBeBuiltIn(log);
        return true;
    }

    @Override
    public Boolean visit(ArrayAccess arrayAccess, Type param) {
        super.visit(arrayAccess, param);
        arrayAccess.setType(arrayAccess.getArray().getType().squareBrackets(arrayAccess.getIndex().getType(), arrayAccess));
        return true;
    }

    @Override
    public Boolean visit(FieldAccess fieldAccess, Type param) {
        super.visit(fieldAccess, param);
        fieldAccess.setType(fieldAccess.getExpression().getType().dot(fieldAccess.getField(), fieldAccess));
        return true;
    }

    @Override
    public Boolean visit(Cast cast, Type param) {
        super.visit(cast, param);
        cast.setType(cast.getTargetType());
        return true;
    }

    @Override
    public Boolean visit(FunctionCall functionCall, Type param) {
        super.visit(functionCall, param);
        functionCall.setType(functionCall.getName().getType().parenthesis(functionCall.getArguments().stream().map(Expression::getType).toList(), functionCall));
        return true;
    }
}
