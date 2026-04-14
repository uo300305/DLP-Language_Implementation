package codegen;

import ast.common.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import semantic.Visitor;

// Visitor que lanza excepcion en cualquiero caso. Para detectar errores
public class AbstractCGVisitor<PT, RT> implements Visitor<PT, RT> {

    protected CodeGenerator cg;

    AbstractCGVisitor(CodeGenerator cg) {
        this.cg = cg;
    }

    @Override
    public RT visit(Program program, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(VarDefinition varDefinition, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(FunctionDefinition functionDefinition, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(Arithmetic arithmetic, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(Logical logical, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(Comparison comparison, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(UnaryMinus unaryMinus, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(UnaryNot unaryNot, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(Variable var, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(CharLiteral charLiteral, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(IntLiteral intLiteral, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(NumberLiteral numberLiteral, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(ArrayAccess arrayAccess, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(FieldAccess fieldAccess, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(Cast cast, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(FunctionCall functionCall, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(While aWhile, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(Return aReturn, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(Log log, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(Assignment assignment, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(IfElse ifElse, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(Input input, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(RecordField recordField, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(VoidType voidType, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(NumberType numberType, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(IntType intType, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(CharType charType, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(RecordType recordType, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(ErrorType errorType, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(ArrayType arrayType, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }

    @Override
    public RT visit(FunctionType functionType, PT param) {
        throw new RuntimeException("Unsupported operation exception");
    }
}
