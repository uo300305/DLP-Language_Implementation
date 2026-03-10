package semantic;

import ast.common.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

// Realmente este visitor se podría implementar como param
// De hecho esto se podría comprobar en el sintáctico
public class LValueVisitor implements Visitor<Void, Void> {

    @Override
    public Void visit(Program program, Void param) {
        program.getDefinitions().forEach(d -> d.accept(this, param));
        return null;
    }

    @Override
    public Void visit(VarDefinition varDefinition, Void param) {
        return null;
    }

    @Override
    public Void visit(FunctionDefinition functionDefinition, Void param) {
        functionDefinition.getBody().forEach(b -> b.accept(this, param));
        return null;
    }

    @Override
    public Void visit(Arithmetic arithmetic, Void param) {
        arithmetic.getLeft().accept(this, param);
        arithmetic.getRight().accept(this, param);
        arithmetic.setlValue(false);
        return null;
    }

    @Override
    public Void visit(Comparison comparison, Void param) {
        comparison.getLeft().accept(this, param);
        comparison.getRight().accept(this, param);
        comparison.setlValue(false);
        return null;
    }

    @Override
    public Void visit(Logical logical, Void param) {
        logical.getLeft().accept(this, param);
        logical.getRight().accept(this, param);
        logical.setlValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryMinus unaryMinus, Void param) {
        unaryMinus.getExpression().accept(this, param);
        unaryMinus.setlValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryNot unaryNot, Void param) {
        unaryNot.getExpression().accept(this, param);
        unaryNot.setlValue(false);
        return null;
    }

    @Override
    public Void visit(Variable var, Void param) {
        var.setlValue(true);
        return null;
    }

    @Override
    public Void visit(CharLiteral charLiteral, Void param) {
        charLiteral.setlValue(false);
        return null;
    }

    @Override
    public Void visit(IntLiteral intLiteral, Void param) {
        intLiteral.setlValue(false);
        return null;
    }

    @Override
    public Void visit(NumberLiteral numberLiteral, Void param) {
        numberLiteral.setlValue(false);
        return null;
    }


    @Override
    public Void visit(ArrayAccess arrayAccess, Void param) {
        arrayAccess.getArray().accept(this, param);
        arrayAccess.getIndex().accept(this, param);
        arrayAccess.setlValue(true);
        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Void param) {
        fieldAccess.getExpression().accept(this, param);
        fieldAccess.setlValue(true);
        return null;
    }

    @Override
    public Void visit(Cast cast, Void param) {
        cast.getExpression().accept(this, param);
        cast.setlValue(true);
        return null;
    }

    @Override
    public Void visit(FunctionCall functionCall, Void param) {
        return null;
    }

    @Override
    public Void visit(While aWhile, Void param) {
        return null;
    }

    @Override
    public Void visit(Return aReturn, Void param) {
        return null;
    }

    @Override
    public Void visit(Log log, Void param) {
        return null;
    }

    @Override
    public Void visit(Assignment assignment, Void param) {
        assignment.getLeft().accept(this, param);
        assignment.getRight().accept(this, param);
        if(!assignment.getLeft().islValue()){
            new ErrorType("esta expresión no puede ir a la derecha de una asignación", assignment.getLeft());
        }
        return null;
    }

    @Override
    public Void visit(IfElse ifElse, Void param) {
        return null;
    }

    @Override
    public Void visit(Input input, Void param) {
        input.getExpression().accept(this, param);
        if(!input.getExpression().islValue()){
            new ErrorType("La expresión no puede usarse como parámetro de un input", input.getExpression());
        }
        return null;
    }

    @Override
    public Void visit(RecordField recordField, Void param) {
        return null;
    }

    @Override
    public Void visit(VoidType voidType, Void param) {
        return null;
    }

    @Override
    public Void visit(NumberType numberType, Void param) {
        return null;
    }

    @Override
    public Void visit(IntType intType, Void param) {
        return null;
    }

    @Override
    public Void visit(CharType charType, Void param) {
        return null;
    }

    @Override
    public Void visit(RecordType recordType, Void param) {
        return null;
    }

    @Override
    public Void visit(ErrorType errorType, Void param) {
        return null;
    }

    @Override
    public Void visit(ArrayType arrayType, Void param) {
        return null;
    }

    @Override
    public Void visit(FunctionType functionType, Void param) {
        return null;
    }
}
