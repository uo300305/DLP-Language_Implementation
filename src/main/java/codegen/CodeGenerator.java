package codegen;

import ast.definitions.VarDefinition;
import ast.types.CharType;
import ast.types.IntType;
import ast.types.NumberType;
import ast.types.Type;

import java.io.IOException;
import java.io.PrintWriter;

// Se le llama para escribir las instrucciones en ensamblador
public class CodeGenerator {
    private int labels = 0;
    private PrintWriter out;

    public CodeGenerator(String outputFilename, String sourceFilename) {
        try {
            this.out=new PrintWriter(outputFilename);
        }
        catch(IOException e) {
            System.err.println("Error opening the file " + outputFilename + ".");
            System.exit(-1);
        }
        this.source(sourceFilename);
    }

    private void source(String sourceFilename) {
        out.println("#source\t\"" + sourceFilename + "\"");
        out.println();
        out.flush();
    }

    public void enter(int bytes) {
        out.println("\tenter\t" + bytes);
        out.flush();
    }

    /**
     * Devolvemos una label para los saltos
     * @return label
     */
    public String getLabel() {
        return "label" + labels++;
    }

    public void jmp(String label) {
        out.println("\tjmp\t" + label);
        out.flush();
    }

    public void jz(String label) {
        out.println("\tjz\t" + label);
        out.flush();
    }

    public void jnz(String label) {
        out.println("\tjnz\t" + label);
        out.flush();
    }

    /**
     *
     * @param returnBytes - los bytes que se devuelven
     * @param localBytes - los bytes de todas las variables locales
     * @param paramBytes - los bytes de todos los parámetros
     */
    public void ret(int returnBytes, int localBytes, int paramBytes) {
        out.println("\tret\t" + returnBytes + ", " + localBytes + ", " + paramBytes);
        out.flush();
    }

    public void callMain() {
        out.println();
        out.println("' Invocation to the main function");
        out.println("call\tmain");
        out.flush();
    }

    public void call(String name) {
        out.println("\tcall\t" + name);
        out.flush();
    }

    public void halt() {
        out.println("halt");
        out.flush();
    }

    /**
     * Para haccer push de los tipos primitivos
     * @param type - Tipo del literal a pushear
     * @param value - Valor a pushear
     */
    public void push(Type type, String value) {
        out.println("\tpush"+type.suffix() + "\t" +value);
        out.flush();
    }

    /**
     * Hace push a una dirección
     * @param value - Valor de la dirección
     */
    public void pusha(int value) {
        out.println("\tpusha\t" + value);
        out.flush();
    }

    /**
     * Hace push al dato que se encuentra en el BP
     */
    public void pushaBP() {
        out.println("\tpush\tbp");
        out.flush();
    }

    public void pop(Type type) {
        out.println("\tpop"+type.suffix());
        out.flush();
    }

    public void load(Type type) {
        out.println("\tload"+type.suffix());
        // Añadir flush al final de cada comando
        out.flush();
    }

    public void store(Type type) {
        out.println("\tstore"+type.suffix());
        out.flush();
    }


    public void convertTo(Type from, Type to) {
        if (from.equals(to)) return;

        if (to.equals(CharType.getInstance())) {

            if (from.equals(IntType.getInstance())) {
                out.println("\ti2b");
                return;
            }

            if (from.equals(NumberType.getInstance())) {
                out.println("\tf2i");
                out.println("\ti2b");
                return;
            }
            throw new RuntimeException("Cannot convert " + from + " -> char");
        }

        if (to.equals(IntType.getInstance())) {

            if (from.equals(CharType.getInstance())) {
                out.println("\tb2i");
                return;
            }

            if (from.equals(NumberType.getInstance())) {
                out.println("\tf2i");
                return;
            }
            throw new RuntimeException("Cannot convert " + from + " -> int");
        }

        if (to.equals(NumberType.getInstance())) {

            if (from.equals(IntType.getInstance())) {
                out.println("\ti2f");
                return;
            }

            if (from.equals(CharType.getInstance())) {
                out.println("\tb2i");
                out.println("\ti2f");
                return;
            }
            throw new RuntimeException("Cannot convert " + from + " -> number");
        }
        throw new RuntimeException("Unsupported conversion: " + from + " -> " + to);
    }


    private void checkNotChar(Type type) {
        if (type.suffix()!='i' && type.suffix()!='f'){
            throw new RuntimeException("Unsupported operation");
        }
    }

    public void add(Type type) {
        checkNotChar(type);
        out.println("\tadd"+type.suffix());
        out.flush();
    }

    public void sub(Type type) {
        checkNotChar(type);
        out.println("\tsub"+type.suffix());
        out.flush();
    }

    public void mul(Type type) {
        checkNotChar(type);
        out.println("\tmul"+type.suffix());
        out.flush();
    }

    public void div(Type type) {
        checkNotChar(type);
        out.println("\tdiv"+type.suffix());
        out.flush();
    }

    public void mod(Type type) {
        checkNotChar(type);
        out.println("\tmod"+type.suffix());
        out.flush();
    }

    /**
     * Switch para operaciones aritmeticas
     * @param type
     * @param operator
     */
    public void arithmetic(Type type, String operator) {
        switch(operator){
            case "+": add(type); break;
            case "-": sub(type); break;
            case "*": mul(type); break;
            case "/": div(type); break;
            case "%": mod(type); break;
            default: throw new RuntimeException("Unsupported operation");
        }
    }

    public void gt(Type type) {
        checkNotChar(type);
        out.println("\tgt"+type.suffix());
        out.flush();
    }

    public void lt(Type type) {
        checkNotChar(type);
        out.println("\tlt"+type.suffix());
        out.flush();
    }

    public void ge(Type type) {
        checkNotChar(type);
        out.println("\tge"+type.suffix());
        out.flush();
    }

    public void le(Type type) {
        checkNotChar(type);
        out.println("\tle"+type.suffix());
        out.flush();
    }

    public void eq(Type type) {
        checkNotChar(type);
        out.println("\teq"+type.suffix());
        out.flush();
    }

    public void ne(Type type) {
        checkNotChar(type);
        out.println("\tne"+type.suffix());
        out.flush();
    }

    public void comparison(Type type, String operator) {
        switch (operator) {
            case ">": gt(type); break;
            case "<": lt(type); break;
            case ">=": ge(type); break;
            case "<=": le(type); break;
            case "==": eq(type); break;
            case "!=": ne(type); break;
            default:
                throw new RuntimeException("Unsupported comparison operator: " + operator);
        }
    }

    public void and() {
        out.println("\tand");
        out.flush();
    }

    public void or() {
        out.println("\tor");
        out.flush();
    }

    public void logical(Type type, String operator) {
        switch (operator) {
            case "&&": and(); break;
            case "||": or(); break;
            default:
                throw new RuntimeException("Unknown logical operator: " + operator);
        }

        out.flush();
    }

    public void not() {
        out.println("\tnot");
        out.flush();
    }

    public void in(Type type) {
        out.println("\tin"+type.suffix());
        out.flush();
    }

    public void out(Type type) {
        out.println("\tout"+type.suffix());
        out.flush();
    }

    // Comments

    public void varDefinition(VarDefinition varDefinition) {
        out.println("\t' * " + varDefinition.getType() + " " + varDefinition.getName() + " (offset " + varDefinition.getOffset() + ")");
    }

    public void line(int line) {
        out.println();
        out.println("#line\t" + line);
        out.flush();
    }

    public void comment(String action) {
        out.println("\t' * " + action);
        out.flush();
    }

    public void parameters() {
        out.println("\t' * Parameters");
    }

    public void localVariables() {
        out.println("\t' * Local variables");
    }

    public void label(String id) {
        out.println();
        out.println(" " + id + ":");
        out.flush();
    }
}
