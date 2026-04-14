package codegen;

import ast.expressions.*;

public class ValueVisitor extends AbstractCGVisitor<Void, Void> {
    private final AddressVisitor address;

    ValueVisitor(CodeGenerator cg, AddressVisitor address){
        super(cg);
        this.address = address;
    }

    @Override
    public Void visit(Cast cast, Void param) {
        cast.getExpression().accept(this, param);
        cg.convertTo(cast.getExpression().getType(), cast.getTargetType());

        return null;
    }

    @Override
    public Void visit(Arithmetic arithmetic, Void param) {
        arithmetic.getLeft().accept(this, param);
        cg.convertTo(arithmetic.getLeft().getType(), arithmetic.getType());

        arithmetic.getRight().accept(this, param);
        cg.convertTo(arithmetic.getRight().getType(), arithmetic.getType());

        cg.arithmetic(arithmetic.getType(), arithmetic.getOperator());

        return null;
    }

    @Override
    public Void visit(Comparison comparison, Void param) {
        comparison.getLeft().accept(this, param);
        cg.convertTo(comparison.getLeft().getType(), comparison.getType());

        comparison.getRight().accept(this, param);
        cg.convertTo(comparison.getRight().getType(), comparison.getType());

        cg.comparison(comparison.getType(), comparison.getOperator());

        return null;
    }

    @Override
    public Void visit(Logical logical, Void param) {
        logical.getLeft().accept(this, param);
        cg.convertTo(logical.getLeft().getType(), logical.getType());

        logical.getRight().accept(this, param);
        cg.convertTo(logical.getRight().getType(), logical.getType());

        cg.logical(logical.getType(), logical.getOperator());

        return null;
    }

    @Override
    public Void visit(UnaryMinus unaryMinus, Void param) {
        cg.push(unaryMinus.getType(), "0");
        unaryMinus.getExpression().accept(this, param);
        cg.sub(unaryMinus.getType());

        return null;
    }

    @Override
    public Void visit(UnaryNot unaryNot, Void param) {
        unaryNot.getExpression().accept(this, param);
        cg.not();

        return null;
    }

    @Override
    public Void visit(Variable var, Void param) {
        var.accept(address, param);
        cg.load(var.getType());

        return null;
    }

    @Override
    public Void visit(IntLiteral intLiteral, Void param) {
        String s = String.valueOf(intLiteral.getValue());
        cg.push(intLiteral.getType(), s);
        return null;
    }

    public Void visit(CharLiteral charLiteral, Void param) {
        String s = String.valueOf((int)charLiteral.getValue());
        cg.push(charLiteral.getType(), s);
        return null;
    }

    public Void visit(NumberLiteral numberLiteral, Void param) {
        String s = String.valueOf(numberLiteral.getValue());
        cg.push(numberLiteral.getType(), s);
        return null;
    }
}
