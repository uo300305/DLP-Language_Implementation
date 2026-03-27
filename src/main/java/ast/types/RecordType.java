package ast.types;

import ast.common.Locatable;
import semantic.Visitor;

import java.util.List;

public class RecordType extends AbstractType {

    private final List<RecordField> fields;

    public RecordType(List<RecordField> fields) {
        this.fields = fields;
    }

    public List<RecordField> getFields() {
        return fields;
    }

    @Override
    public Type squareBrackets(Type other, Locatable locatable) {
        return this;
    }

    public Type dot(String name, Locatable locatable) {
        for(RecordField f: this.fields) {
            if(f.getName().equals(name)){
                return f.getType();
            }
        }
        return new ErrorType("El campo '" + name + "' no existe en el record", locatable);
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }

    @Override
    public String toString() {
        return "record";
    }
}
