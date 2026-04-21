package ast.types;

import ast.common.Locatable;
import semantic.Visitor;

import java.util.List;
import java.util.stream.Collectors;

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

    public RecordField getField(String name) {
        for(RecordField f: this.fields) {
            if(f.getName().equals(name)){
                return f;
            }
        }

        // Nunca debería ejecutarse
        return null;
    }

    @Override
    public int getNumberOfBytes() {
        return fields.stream().map(f -> f.getType().getNumberOfBytes()).reduce(0, Integer::sum);
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> visitor, PT param) {
        return visitor.visit(this, param);
    }

    @Override
    public String toString() {
        String fields = this.fields.stream()
                .map(RecordField::toString)
                .collect(Collectors.joining(", "));
        return "RecordType[fields:[" + fields + "]]";
    }
}
