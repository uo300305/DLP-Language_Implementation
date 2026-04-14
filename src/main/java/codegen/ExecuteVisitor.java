package codegen;

import ast.common.Program;
import ast.definitions.Definition;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.statements.Assignment;
import ast.statements.Input;
import ast.statements.Log;
import ast.statements.Statement;
import ast.types.FunctionType;

public class ExecuteVisitor extends AbstractCGVisitor<Void, Void> {
    private final AddressVisitor address;
    private final ValueVisitor value;

    public ExecuteVisitor(CodeGenerator cg) {
        super(cg);
        this.address=new AddressVisitor(cg);
        this.value= new ValueVisitor(cg, address);
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
        cg.action("Assignment");
        assignment.getLeft().accept(address, param);
        assignment.getRight().accept(value, param);
        cg.convertTo(assignment.getRight().getType(), assignment.getLeft().getType());
        cg.store(assignment.getLeft().getType());

        return null;
    }

    @Override
    public Void visit(Input input, Void param) {
        cg.line(input.getLine());
        cg.action("Read");
        input.getExpression().accept(address, param);
        cg.in(input.getExpression().getType());
        cg.store(input.getExpression().getType());

        return null;
    }

    @Override
    public Void visit(Log log, Void param) {
        cg.line(log.getLine());
        cg.action("Write");
        log.getExpression().accept(value, param);
        cg.out(log.getExpression().getType());

        return null;
    }

}
