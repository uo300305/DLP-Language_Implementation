package semantic;

import ast.definitions.FunctionDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

// Realmente este visitor se podría implementar como param
// De hecho esto se podría comprobar en el sintáctico
public class LValueVisitor extends AbstractVisitor<Void, Void> {

    @Override
    public Void visit(FunctionDefinition functionDefinition, Void param) {
        functionDefinition.getBody().forEach(b -> b.accept(this, param));
        return null;
    }

    @Override
    public Void visit(Arithmetic arithmetic, Void param) {
        super.visit(arithmetic, param); // delega el recorrido
        arithmetic.setlValue(false);
        return null;
    }

    @Override
    public Void visit(Comparison comparison, Void param) {
        super.visit(comparison, param);
        comparison.setlValue(false);
        return null;
    }

    @Override
    public Void visit(Logical logical, Void param) {
        super.visit(logical, param);
        logical.setlValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryMinus unaryMinus, Void param) {
        super.visit(unaryMinus, param);
        unaryMinus.setlValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryNot unaryNot, Void param) {
        super.visit(unaryNot, param);
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
        super.visit(arrayAccess, param);
        arrayAccess.setlValue(true);
        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Void param) {
        super.visit(fieldAccess, param);
        fieldAccess.setlValue(true);
        return null;
    }

    @Override
    public Void visit(Cast cast, Void param) {
        super.visit(cast, param);
        cast.setlValue(true);
        return null;
    }

    @Override
    public Void visit(Assignment assignment, Void param) {
        super.visit(assignment, param);
        if (!assignment.getLeft().islValue())
            new ErrorType("La expresión no puede ir a la izquierda de una asignación", assignment.getLeft());
        return null;
    }

    @Override
    public Void visit(Input input, Void param) {
        super.visit(input, param);
        if (!input.getExpression().islValue())
            new ErrorType("La expresión no puede usarse como parámetro de un input", input.getExpression());
        return null;
    }
}
