package codegen;

import ast.common.Program;
import ast.definitions.Definition;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.statements.*;
import ast.types.FunctionType;
import ast.types.IntType;

public class ExecuteVisitor extends AbstractCGVisitor<Void, Void> {
    private final AddressVisitor address;
    private final ValueVisitor value;

    public ExecuteVisitor(CodeGenerator cg) {
        super(cg);
        this.address=new AddressVisitor(cg);
        this.value= new ValueVisitor(cg);

        this.address.setValue(value);
        this.value.setAddress(address);
    }

    @Override
    public Void visit(Program program, Void param) {
        for (Definition var : program.getDefinitions()) {
            if(var instanceof VarDefinition) {
                var.accept(this, param);
            }
        }

        cg.callMain();
        cg.halt();

        for (Definition var : program.getDefinitions()) {
            if(var instanceof FunctionDefinition) {
                var.accept(this, param);
            }
        }

        return null;
    }


    @Override
    public Void visit(FunctionDefinition functionDefinition, Void param) {
        cg.line(functionDefinition.getLine());
        cg.label(functionDefinition.getName());

        cg.parameters();
        for (VarDefinition paramVar :
                ((FunctionType) functionDefinition.getType()).getParameters()) {
            paramVar.accept(this, param);
        }

        int localBytes = 0;
        for (VarDefinition varDefinition : functionDefinition.getDefinitions()) {
            varDefinition.accept(this, param);
            localBytes += varDefinition.getType().getNumberOfBytes();
        }

        cg.enter(localBytes);

        for (Statement statement : functionDefinition.getBody()) {
            statement.accept(this, param);
        }

        // TODO De momento se simplifica así, cambiar
        int retBytes = 0;

        int paramBytes =
                ((FunctionType) functionDefinition.getType())
                        .getParameters()
                        .stream()
                        .mapToInt(v -> v.getType().getNumberOfBytes())
                        .sum();

        cg.ret(retBytes, localBytes, paramBytes);

        return null;
    }


    @Override
    public Void visit(VarDefinition varDefinition, Void param) {
        cg.varDefinition(varDefinition);
        return null;
    }

    @Override
    public Void visit(Assignment assignment, Void param) {
        cg.line(assignment.getLine());
        cg.comment("Assignment");
        assignment.getLeft().accept(address, param);
        assignment.getRight().accept(value, param);
        cg.convertTo(assignment.getRight().getType(), assignment.getLeft().getType());
        cg.store(assignment.getLeft().getType());

        return null;
    }

    @Override
    public Void visit(Input input, Void param) {
        cg.line(input.getLine());
        cg.comment("Read");
        input.getExpression().accept(address, param);
        cg.in(input.getExpression().getType());
        cg.store(input.getExpression().getType());

        return null;
    }

    @Override
    public Void visit(Log log, Void param) {
        cg.line(log.getLine());
        cg.comment("Write");
        log.getExpression().accept(value, param);
        cg.out(log.getExpression().getType());

        return null;
    }

    @Override
    public Void visit(While w, Void param) {
        cg.line(w.getLine());
        cg.comment("While");
        String cond = cg.getLabel();
        String end = cg.getLabel();

        cg.line(w.getLine());
        cg.label(cond);
        w.getCondition().accept(value, param);
        cg.convertTo(w.getCondition().getType(), IntType.getInstance());
        cg.jz(end);
        cg.comment("While body");
        w.getBody().forEach(s -> s.accept(this, param));
        cg.jmp(cond);
        cg.label(end);

        return null;
    }

    @Override
    public Void visit(IfElse i, Void param) {
        cg.line(i.getLine());
        cg.comment("If");

        String els = cg.getLabel();
        String end = cg.getLabel();

        cg.line(i.getLine());
        i.getCondition().accept(value, param);
        cg.convertTo(i.getCondition().getType(), IntType.getInstance());
        cg.jz(els);
        cg.comment("If body");
        i.getIfBody().forEach(s -> s.accept(this, param));
        cg.jmp(end);
        cg.label(els);
        cg.comment("else body");
        i.getElseBody().forEach(s -> s.accept(this, param));
        cg.label(end);


        return null;
    }

}
