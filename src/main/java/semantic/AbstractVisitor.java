package semantic;

import ast.common.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

public abstract class AbstractVisitor<RT, PT> implements Visitor<PT, RT> {

    @Override
    public RT visit(Program program, PT param) {
        program.getDefinitions().forEach(d -> d.accept(this, param));
        return null;
    }

    @Override
    public RT visit(VarDefinition varDefinition, PT param) {
        varDefinition.getType().accept(this, param);
        return null;
    }

    @Override
    public RT visit(FunctionDefinition functionDefinition, PT param) {
        functionDefinition.getType().accept(this, param);
        functionDefinition.getParameters().forEach(d -> d.accept(this, param));
        functionDefinition.getBody().forEach(s -> s.accept(this, param));
        return null;
    }

    @Override
    public RT visit(Arithmetic arithmetic, PT param) {
        arithmetic.getLeft().accept(this, param);
        arithmetic.getRight().accept(this, param);
        return null;
    }

    @Override
    public RT visit(Logical logical, PT param) {
        logical.getLeft().accept(this, param);
        logical.getRight().accept(this, param);
        return null;
    }

    @Override
    public RT visit(Comparison comparison, PT param) {
        comparison.getLeft().accept(this, param);
        comparison.getRight().accept(this, param);
        return null;
    }

    @Override
    public RT visit(UnaryMinus unaryMinus, PT param) {
        unaryMinus.getExpression().accept(this, param);
        return null;
    }

    @Override
    public RT visit(UnaryNot unaryNot, PT param) {
        unaryNot.getExpression().accept(this, param);
        return null;
    }

    @Override
    public RT visit(Variable var, PT param) {
        return null;
    }

    @Override
    public RT visit(CharLiteral charLiteral, PT param) {
        return null;
    }

    @Override
    public RT visit(IntLiteral intLiteral, PT param) {
        return null;
    }

    @Override
    public RT visit(NumberLiteral numberLiteral, PT param) {
        return null;
    }

    @Override
    public RT visit(ArrayAccess arrayAccess, PT param) {
        arrayAccess.getArray().accept(this, param);
        arrayAccess.getIndex().accept(this, param);
        return null;
    }

    @Override
    public RT visit(FieldAccess fieldAccess, PT param) {
        fieldAccess.getExpression().accept(this, param);
        return null;
    }

    @Override
    public RT visit(Cast cast, PT param) {
        cast.getExpression().accept(this, param);
        return null;
    }

    @Override
    public RT visit(FunctionCall functionCall, PT param) {
        functionCall.getName().accept(this, param);
        functionCall.getArguments().forEach(a -> a.accept(this, param));
        return null;
    }

    @Override
    public RT visit(While aWhile, PT param) {
        aWhile.getCondition().accept(this, param);
        aWhile.getBody().forEach(s -> s.accept(this, param));
        return null;
    }

    @Override
    public RT visit(Return aReturn, PT param) {
        aReturn.getExpression().accept(this, param);
        return null;
    }

    @Override
    public RT visit(Log log, PT param) {
        log.getExpression().accept(this, param);
        return null;
    }

    @Override
    public RT visit(Assignment assignment, PT param) {
        assignment.getLeft().accept(this, param);
        assignment.getRight().accept(this, param);
        return null;
    }

    @Override
    public RT visit(IfElse ifElse, PT param) {
        ifElse.getCondition().accept(this, param);
        ifElse.getIfBody().forEach(s -> s.accept(this, param));
        ifElse.getElseBody().forEach(s -> s.accept(this, param));
        return null;
    }

    @Override
    public RT visit(Input input, PT param) {
        input.getExpression().accept(this, param);
        return null;
    }

    @Override
    public RT visit(RecordField recordField, PT param) {
        recordField.getType().accept(this, param);
        return null;
    }

    @Override
    public RT visit(RecordType recordType, PT param) {
        recordType.getFields().forEach(f -> f.accept(this, param));
        return null;
    }

    @Override
    public RT visit(ArrayType arrayType, PT param) {
        arrayType.getElementType().accept(this, param);
        arrayType.getDimension().accept(this, param);
        return null;
    }

    @Override
    public RT visit(FunctionType functionType, PT param) {
        functionType.getParameters().forEach(p -> p.accept(this, param));
        functionType.getType().accept(this, param);
        return null;
    }

    @Override
    public RT visit(VoidType voidType, PT param) {
        return null;
    }

    @Override
    public RT visit(NumberType numberType, PT param) {
        return null;
    }

    @Override
    public RT visit(IntType intType, PT param) {
        return null;
    }

    @Override
    public RT visit(CharType charType, PT param) {
        return null;
    }

    @Override
    public RT visit(ErrorType errorType, PT param) {
        return null;
    }
}