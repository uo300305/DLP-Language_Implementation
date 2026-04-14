package codegen;

import ast.definitions.VarDefinition;
import ast.expressions.Variable;
import ast.types.IntType;

public class AddressVisitor extends AbstractCGVisitor<Void, Void> {
    AddressVisitor(CodeGenerator cg){
        super(cg);
    }

    @Override
    public Void visit(Variable var, Void param) {
        // TODO revisar esto que me huele bastante mal
        if (var.getDefinition() instanceof VarDefinition vd) {
            if (var.getDefinition().getScope() == 0) {
                cg.pusha(vd.getOffset());
            } else {
                cg.pushaBP();
                cg.push(var.getType(), String.valueOf(vd.getOffset()));
                cg.add(IntType.getInstance());
            }
        }

        return null;
    }
}
