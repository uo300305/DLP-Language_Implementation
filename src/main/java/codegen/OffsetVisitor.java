package codegen;

import ast.definitions.VarDefinition;
import ast.types.FunctionType;
import ast.types.RecordField;
import ast.types.RecordType;
import semantic.AbstractVisitor;

public class OffsetVisitor extends AbstractVisitor<Void, Void> {

    private int byteGlobalSum = 0;
    private int byteLocalSum = 4;

    /*
     * El offset es un desplazamiento, el número a sumarle a una dirección de referencia para
     * llegar a la variable buscada:
     * - En las globales el BP (base pointer) es 0 SIEMPRE, estático.
     *   Es decir, no depende del tamaño de las variables posteriores
     *   Formula: Of(v) = Sumatorio vd.type.numberOfBytes();
     *   vd pertenece a previous(v)
     *
     * - En las locales el BP varía en función de a qué dirección apunta en memoria.
     *   Es decir, depende del tamaño de las previas
     *   Formula: Of(v) = Sumatorio - vd.type.getNumberOfBytes();
     *   vd pertenece a previous(v) U v
     *
     *  - Para parámetros:
     *    Formula: Of(i) = 4 + Sumatorio vd.type.getNumberOfBytes()
     *    vd pertenece a posteriores(v);
     *
     *  - Para parámetros de los records (o sea fields):
     *    Formula: Of(f) = Sumatorio rp.type.getNumberOfBytes()
     *    vp pertenece a previous(f)
     *
     * Hay 4 bytes de control en la memoria dinámica bajo el orden de n hacia 0:
     * ID (2 bytes)
     * BP (2 bytes)
     * ...
     * La info de control se apila con cada cambio de función. Primero main, luego por orden...
     *
     * Posterior hacia 0, anterior hacia N
     */

    /*
     * getOffset(): int
     * setOffset(int): void
     * RecordFields y VarDefinition
     */


    /* Podemos hacerlo así pq no se puede pasar como parámetro un recordType
     * Al hacerlo así es más sencillo pero rompe la filosofía al no visitar a todos los hijos
     * Para no pasarle params a varDefinition
     */
    @Override
    public Void visit(FunctionType functionType, Void param) {
        // Inicializamos a 4 por los bytes de control
        byteLocalSum = 0;
        int byteLocalSum = 4;
        var params = functionType.getParameters();
        // OJO hay obtenerlos al revés
        for (int i = params.size() - 1; i >= 0; i--) {
            var p = params.get(i);
            p.setOffset(byteLocalSum);
            byteLocalSum += p.getType().getNumberOfBytes();
        }

        return null;
    }

    @Override
    public Void visit(VarDefinition varDefinition, Void param) {
        super.visit(varDefinition, param);

        if(varDefinition.getScope() == 0) {
            varDefinition.setOffset(byteGlobalSum);
            byteGlobalSum += varDefinition.getType().getNumberOfBytes();
        }
        else {
            byteLocalSum = byteLocalSum + varDefinition.getType().getNumberOfBytes();
            varDefinition.setOffset(-byteLocalSum);
        }
        return null;
    }

    @Override
    public Void visit(RecordType recordType, Void param) {
        int bytesFieldsSum = 0;
        for(RecordField field: recordType.getFields()) {
            field.setOffset(bytesFieldsSum);
            bytesFieldsSum+=field.getType().getNumberOfBytes();

            // OJO si tenemos recordType como field hay que mirarlo
            field.getType().accept(this, param);
        }
        return null;
    }
}
