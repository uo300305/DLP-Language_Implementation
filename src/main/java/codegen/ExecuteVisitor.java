package codegen;

import ast.common.Program;
import ast.definitions.Definition;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.FunctionCall;
import ast.statements.*;
import ast.types.FunctionType;
import ast.types.IntType;
import ast.types.Type;
import ast.types.VoidType;

public class ExecuteVisitor extends AbstractCGVisitor<FunctionDefinition, Void> {
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
    public Void visit(Program program, FunctionDefinition param) {
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
    public Void visit(FunctionDefinition functionDefinition, FunctionDefinition param) {
        cg.line(functionDefinition.getLine());
        cg.label(functionDefinition.getName());

        cg.parameters();
        for (VarDefinition paramVar :
                ((FunctionType) functionDefinition.getType()).getParameters()) {
            paramVar.accept(this, param);
        }

        cg.localVariables();
        int localBytes = 0;
        for (VarDefinition varDefinition : functionDefinition.getDefinitions()) {
            varDefinition.accept(this, param);
            localBytes += varDefinition.getType().getNumberOfBytes();
        }

        cg.enter(localBytes);

        for (Statement statement : functionDefinition.getBody()) {
            statement.accept(this, functionDefinition);
        }

        FunctionType functionType = ((FunctionType)functionDefinition.getType());

        if(functionType.getType()==VoidType.getInstance()) {
            cg.ret(0, localBytes,functionType.getParameters().stream().mapToInt(p -> p.getType().getNumberOfBytes()).sum());
        }

        return null;
    }

    public Void visit(Return aReturn, FunctionDefinition functionDefinition) {
        cg.line(aReturn.getLine());
        cg.comment("Return");

        aReturn.getExpression().accept(value, null);
        FunctionType functionType = ((FunctionType)functionDefinition.getType());
        cg.convertTo(aReturn.getExpression().getType(), functionType.getType());
        int paramSum = functionType.getParameters().stream().mapToInt(p -> p.getType().getNumberOfBytes()).sum();
        int localVariableSum = functionDefinition.getDefinitions().stream().mapToInt(p -> p.getType().getNumberOfBytes()).sum();
        cg.ret(functionType.getType().getNumberOfBytes(), localVariableSum, paramSum);

        return null;
    }


    @Override
    public Void visit(VarDefinition varDefinition, FunctionDefinition param) {
        cg.varDefinition(varDefinition);
        return null;
    }

    @Override
    public Void visit(Assignment assignment, FunctionDefinition param) {
        cg.line(assignment.getLine());
        cg.comment("Assignment");
        assignment.getLeft().accept(address, null);
        assignment.getRight().accept(value, null);
        cg.convertTo(assignment.getRight().getType(), assignment.getLeft().getType());
        cg.store(assignment.getLeft().getType());

        return null;
    }

    @Override
    public Void visit(Input input, FunctionDefinition param) {
        cg.line(input.getLine());
        cg.comment("Read");
        input.getExpression().accept(address, null);
        cg.in(input.getExpression().getType());
        cg.store(input.getExpression().getType());

        return null;
    }

    @Override
    public Void visit(Log log, FunctionDefinition param) {
        cg.line(log.getLine());
        cg.comment("Write");
        log.getExpression().accept(value, null);
        cg.out(log.getExpression().getType());

        return null;
    }

    @Override
    public Void visit(While w, FunctionDefinition param) {
        cg.line(w.getLine());
        cg.comment("While");
        String cond = cg.getLabel();
        String end = cg.getLabel();

        cg.line(w.getLine());
        cg.label(cond);
        w.getCondition().accept(value, null);
        cg.convertTo(w.getCondition().getType(), IntType.getInstance());
        cg.jz(end);
        cg.comment("While body");
        w.getBody().forEach(s -> s.accept(this, param));
        cg.jmp(cond);
        cg.label(end);

        return null;
    }

    @Override
    public Void visit(IfElse i, FunctionDefinition param) {
        cg.line(i.getLine());
        cg.comment("If");

        String els = cg.getLabel();
        String end = cg.getLabel();

        cg.line(i.getLine());
        i.getCondition().accept(value, null);
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

    public Void visit(FunctionCall functionCall, FunctionDefinition param) {
        cg.line(functionCall.getLine());

        functionCall.accept(value, null);
        Type returnType = functionCall.getType();
        if(returnType!= VoidType.getInstance()) {
            cg.pop(returnType);
        }

        return null;
    }

}
