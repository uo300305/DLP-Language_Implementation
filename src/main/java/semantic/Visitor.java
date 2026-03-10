package semantic;

import ast.common.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

// param y ReturnType
public interface Visitor<PT, RT> {

    RT visit(Program program, PT param);

    RT visit(VarDefinition varDefinition, PT param);

    RT visit(FunctionDefinition functionDefinition, PT param);

    RT visit(Arithmetic arithmetic, PT param);

    RT visit(Logical logical, PT param);

    RT visit(Comparison comparison, PT param);

    RT visit(UnaryMinus unaryMinus, PT param);

    RT visit(UnaryNot unaryNot, PT param);

    RT visit(Variable var, PT param);

    RT visit(CharLiteral charLiteral, PT param);

    RT visit(IntLiteral intLiteral, PT param);

    RT visit(NumberLiteral numberLiteral, PT param);

    RT visit(ArrayAccess arrayAccess, PT param);

    RT visit(FieldAccess fieldAccess, PT param);

    RT visit(Cast cast, PT param);

    RT visit(FunctionCall functionCall, PT param);

    RT visit(While aWhile, PT param);

    RT visit(Return aReturn, PT param);

    RT visit(Log log, PT param);

    RT visit(Assignment assignment, PT param);

    RT visit(IfElse ifElse, PT param);

    RT visit(Input input, PT param);

    RT visit(RecordField recordField, PT param);
    
    RT visit(VoidType voidType, PT param);

    RT visit(NumberType numberType, PT param);

    RT visit(IntType intType, PT param);

    RT visit(CharType charType, PT param);

    RT visit(RecordType recordType, PT param);

    RT visit(ErrorType errorType, PT param);

    RT visit(ArrayType arrayType, PT param);

    RT visit(FunctionType functionType, PT param);
}
