package codegen;

import ast.definitions.VarDefinition;
import ast.expressions.ArrayAccess;
import ast.expressions.FieldAccess;
import ast.expressions.Variable;
import ast.types.IntType;
import ast.types.RecordType;

public class AddressVisitor extends AbstractCGVisitor<Void, Void> {
    private ValueVisitor value;

    AddressVisitor(CodeGenerator cg){
        super(cg);
    }

    public void setValue(ValueVisitor vv) {
        this.value = vv;
    }

    /**
     * void address [[Variable: expression -> ID]]():
     *     if(expression.definition.scope == 0)
     *         <pusha> expression.definition.offset
     *     else{
     *         <push BP>
     *         <pushi> expression.definition.offset
     *         <addi>
     *     }
     */
    @Override
    public Void visit(Variable var, Void param) {
        if (var.getDefinition() instanceof VarDefinition vd) {
            if (var.getDefinition().getScope() == 0) {
                cg.pusha(vd.getOffset());
            } else {
                cg.pushaBP();
                cg.push(IntType.getInstance(), String.valueOf(vd.getOffset()));
                cg.add(IntType.getInstance());
            }
        }

        return null;
    }

    /**
     * void address [[ArrayAccess: expression1 -> expression2 expression3]]():
     *     address [[expression2]]()
     *     value [[expression3]]()
     *     cg.convertTo(expression3.type, IntType)
     *     <pushi> expression1.type.numberOfBytes
     *     <muli>
     *     <addi>
     */
    @Override
    public Void visit(ArrayAccess aa, Void param) {
        aa.getArray().accept(this, param);
        aa.getIndex().accept(value, param);
        cg.convertTo(aa.getIndex().getType(), IntType.getInstance());
        cg.push(IntType.getInstance(), String.valueOf(aa.getType().getNumberOfBytes()));
        cg.mul(IntType.getInstance());
        cg.add(IntType.getInstance());

        return null;
    }

    /**
     * void address [[FieldAccess: expression1 -> expression2 ID]]():
     *     address [[expression2]]()
     *     <pushi> recordType.getField(ID).offset
     *     <addi>
     */
    @Override
    public Void visit(FieldAccess fa, Void param) {
        RecordType rt = (RecordType)fa.getExpression().getType();

        fa.getExpression().accept(this, param);
        cg.push(IntType.getInstance(), String.valueOf(rt.getField(fa.getField()).getOffset()));
        cg.add(IntType.getInstance());

        return null;
    }
}
