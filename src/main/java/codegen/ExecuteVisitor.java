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

    /**
     * void execute [[Program: program -> definition*]]():
     *     definition*.forEach(d -> {
     *         if (d instanceof VarDefinition)
     *         execute[[d]]()
     *     })
     *     <call main>
     *     <halt>
     *     definition*.forEach(d -> {
     *         if (d instanceof FuncDefinition)
     *             execute[[d]]()
     *     })
     */
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

    /**
     * execute[[FunctionDefinition: definition -> ID type statement*]]():
     * ID <:>
     *  ' * Parameters
     *  execute[[type]]()
     *  ' * Local variables
     *  statement*.forEach(stmt ->
     *      if (stmt instanceof Variable)
     *          execute[[stmt]]()
     *  )
     *  <enter> definition.localVarBytes
     *  statement*.forEach(stmt ->
     *      if (!(stmt instanceof Variable))
     *          execute[[stmt]]()
     *  )
     *  if (type.returnType instanceof VoidType)
     *     <ret> 0 <,>
     *       funcDefinition.getDefinitions().stream().mapToInt(p -> p.getType().getNumberOfBytes()).sum() <,>
     *       funcDefinition.getParameters().stream().mapToInt(p -> p.getType().getNumberOfBytes()).sum();
     */
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

    /**
     * void execute[[Return: statement -> expression]](funcDefinition):
     *  value[[expression]]
     *  cg.convertTo(exp.type, funcDefinition.type.returnType)
     *
     *  <ret> funcDefinition.type.returnType.numberOfBytes <,>
     *      funcDefinition.getDefinitions().stream().mapToInt(p -> p.getType().getNumberOfBytes()).sum() <,>
     *      funcDefinition.getParameters().stream().mapToInt(p -> p.getType().getNumberOfBytes()).sum();
     */
    public Void visit(Return aReturn, FunctionDefinition functionDefinition) {
        cg.line(aReturn.getLine());
        cg.comment("Return");

        aReturn.getExpression().accept(value, null);
        FunctionType functionType = ((FunctionType)functionDefinition.getType());
        cg.convertTo(aReturn.getExpression().getType(), functionType.getType());
        int localVariableSum = functionDefinition.getDefinitions().stream().mapToInt(p -> p.getType().getNumberOfBytes()).sum();
        int paramSum = functionType.getParameters().stream().mapToInt(p -> p.getType().getNumberOfBytes()).sum();
        cg.ret(functionType.getType().getNumberOfBytes(), localVariableSum, paramSum);

        return null;
    }


    /**
     * void execute[[VarDefinition: definition -> type ID]]():
     *     cg.varDefinition(definition)
     */
    @Override
    public Void visit(VarDefinition varDefinition, FunctionDefinition param) {
        cg.varDefinition(varDefinition);
        return null;
    }

    /**
     * void execute[[Assignment: statement -> expression1 expresion2]]():
     * ' * Assignment
     * address[[expression1]]()
     * value[[expression2]]()
     * cg.convertTo(expression1.type, expression2.type)
     * <store> expresion1.type.suffix
     */
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

    /**
     * void execute[[Input: statement -> expression]]():
     * ' * Read
     * address[[expression]]()
     * <in> expresion.type.suffix
     * <store> expresion.type.suffix
     */
    @Override
    public Void visit(Input input, FunctionDefinition param) {
        cg.line(input.getLine());
        cg.comment("Read");
        input.getExpression().accept(address, null);
        cg.in(input.getExpression().getType());
        cg.store(input.getExpression().getType());

        return null;
    }

    /**
     * void execute[[Log: statement -> expression]]():
     * <'> * Write
     * value[[expression]]()
     * <out> expresion.type.suffix
     */
    @Override
    public Void visit(Log log, FunctionDefinition param) {
        cg.line(log.getLine());
        cg.comment("Write");
        log.getExpression().accept(value, null);
        cg.out(log.getExpression().getType());

        return null;
    }

    /**
     * void execute[[While: statement -> expression statement*]]():
     *  String condition = cg.getLabel()
     *  String end = cg.getLabel()
     *  condition <:>
     *  value[[expression]]()
     *  jz end
     *  statement*.forEach(statement -> execute[[statement]]())
     *  jmp condition
     *  end <:>
     */
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

    /**
     * void execute[[IfElse: statement -> expression statement1* statement2*]]():
     *  String else = cg.nextLabel()
     *  String end = cg.nextLabel()
     *  value[[expression]]()
     *  jz else
     *  statement1*.forEach(stmt -> execute[[stmt]]())
     *  jmp end
     *  <else> :
     *  statement2*.forEach(stmt -> execute[[stmt]]())
     *  <end> :
     */
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


    // La única diferencia entre el execute y el value es que aquí, en caso de no ser void,
    // quitamos el valor de la pila
    /**
     * void execute[[FunctionCall: statement -> expression1 expression2*]]():
     *  value[[(expression) statement]]()
     *  if (!(expression1.type.returnType instanceof VoidType))
     *      <pop> expression1.type.returnType.suffix
     */
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
