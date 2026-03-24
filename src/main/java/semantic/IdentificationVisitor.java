package semantic;

import ast.definitions.Definition;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.types.*;
import symboltable.SymbolTable;

import java.util.HashSet;
import java.util.Set;

public class IdentificationVisitor extends AbstractVisitor<Void, Void> {

    // Para comprobar el ámbito de las variables
    private SymbolTable table = new SymbolTable();
    // Para comprobar que no haya recordFields repetidos
    Set<String> set;

    @Override
    public Void visit(VarDefinition varDefinition, Void param) {
        if (!table.insert(varDefinition))
            new ErrorType("No pueden declararse dos variables con el mismo nombre", varDefinition);
        varDefinition.getType().accept(this, param); // llamada explícita porque super no inserta
        return null;
    }

    @Override
    public Void visit(FunctionDefinition functionDefinition, Void param) {
        if (!table.insert(functionDefinition))
            new ErrorType("No pueden declararse dos variables con el mismo nombre", functionDefinition);
        table.set();
        functionDefinition.getType().accept(this, param);
        functionDefinition.getDefinitions().forEach(d -> d.accept(this, param));
        functionDefinition.getBody().forEach(s -> s.accept(this, param));
        table.reset();
        return null;
    }

    @Override
    public Void visit(Variable var, Void param) {
        Definition vd = table.find(var.getName());
        if (vd == null)
            new ErrorType("La variable debe ser definida antes de usarse", var);
        else
            var.setDefinition(vd);
        return null;
    }

    @Override
    public Void visit(RecordField recordField, Void param) {
        if (!set.add(recordField.getName()))
            new ErrorType("Variables duplicadas", recordField);
        recordField.getType().accept(this, param);
        return null;
    }

    @Override
    public Void visit(RecordType recordType, Void param) {
        set = new HashSet<>();
        recordType.getFields().forEach(f -> f.accept(this, param));
        return null;
    }
}