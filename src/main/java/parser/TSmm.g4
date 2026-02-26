grammar TSmm;
@header{
    import ast.expressions.*;
    import ast.common.*;
    import ast.definitions.*;
    import ast.statements.*;
    import ast.types.*;
}

// Forzamos a que pare en el EOF
program returns [Program ast] locals[List<Definition> defs = new ArrayList<>(), List<VarDefinition> vars = new ArrayList<>(), List<Statement> stmts = new ArrayList<>()]:
        (def1=definition{$defs.addAll($def1.ast); })* F='function' MAIN='main' '(' ')' ':' 'void' '{' (vd=variable_definition {$vars.addAll($vd.ast); })* (s1=statement{$stmts.addAll($s1.ast); })* '}' {$defs.add(new FunctionDefinition($MAIN.text, new FunctionType(VoidType.getInstance(), new ArrayList<VarDefinition>()), $vars, $stmts, $F.getLine(), $F.getCharPositionInLine()+1)); } EOF {$ast = new Program($defs); }
       ;

definition returns [List<Definition> ast = new ArrayList<>()] locals [List<VarDefinition> defs = new ArrayList<>(), List<Statement> stmts = new ArrayList<>()] :
            F='function' ID ft=function_type '{' (vd=variable_definition {$defs.addAll($vd.ast);})* (s1=statement{$stmts.addAll($s1.ast);})* '}' {$ast.add(new FunctionDefinition($ID.text, $ft.ast, $defs, $stmts, $F.getLine(), $F.getCharPositionInLine()+1)); }
            | vd=variable_definition {$ast.addAll($vd.ast); }
            ;

variable_definition returns [List<VarDefinition> ast = new ArrayList<>()] locals [List<String> vars = new ArrayList<>()]:
    LET='let' ID1=ID {$vars.add($ID1.text); } (',' ID2=ID
    {
        $vars.add($ID2.text); })* ':' type ';' {$vars.stream().forEach(v -> $ast.add(new VarDefinition(v, $type.ast, $LET.getLine(), $LET.getCharPositionInLine()+1)));
    }
    ;

 type returns [Type ast] locals [List<RecordField> rfs = new ArrayList<>()]:
        ft=function_type {$ast = $ft.ast; }
       | '['INT_CONSTANT']' type {$ast = new ArrayType(LexerHelper.lexemeToInt($INT_CONSTANT.text), $type.ast); }
       | '['(vd=variable_definition {$vd.ast.stream().forEach(v->$rfs.add(new RecordField(v.getName(), v.getType()))); })+']' {$ast = new RecordType($rfs); }
       | st=simple_type {$ast=$st.ast; }
       ;

function_type returns [FunctionType ast] locals [List<VarDefinition> params = new ArrayList<>(), Type t = null; ]:
            '(' ID1=ID ':' st1=simple_type {$params.add(new VarDefinition($ID1.text, $st1.ast, $ID1.getLine(), $ID1.getCharPositionInLine()+1)); } (',' ID2=ID ':' st2=simple_type {$params.add(new VarDefinition($ID2.text, $st2.ast, $ID2.getLine(), $ID2.getCharPositionInLine()+1));})* ')' ':' (type {$t=$type.ast; } | 'void' {$t=VoidType.getInstance(); }) {$ast = new FunctionType($t, $params); }
            ;

simple_type returns [Type ast]:
    'int' {$ast=IntType.getInstance(); }
    | 'number' {$ast=NumberType.getInstance(); }
    | 'char' {$ast=CharType.getInstance(); }
    ;

statement returns [List<Statement> ast = new ArrayList<>()] locals [List<Statement> else=new ArrayList<>(), List<Expression> exps = new ArrayList<>()]:
        ID '('(es=expression_list {$exps.addAll($es.ast); })?')' ';' {$ast.add(new FunctionCall(new Variable($ID.text, $ID.getLine(), $ID.getCharPositionInLine()+1), $exps, $ID.getLine(), $ID.getCharPositionInLine()+1)); }
        | LOG='log' el=expression_list ';'
            {
                $el.ast.stream().forEach(e -> $ast.add(new Log(e, $LOG.getLine(), $LOG.getCharPositionInLine()+1)));
            }
        | WHILE='while' '(' e1=expression ')' body {$ast.add(new While($e1.ast, $body.ast, $WHILE.getLine(), $WHILE.getCharPositionInLine()+1)); }
        | e1=expression '=' e2=expression ';' {$ast.add(new Assignment($e1.ast, $e2.ast, $e1.ast.getLine(), $e1.ast.getColumn())); }
        | INPUT='input' el=expression_list ';'
            {
                $el.ast.stream().forEach(e -> $ast.add(new Input(e, $INPUT.getLine(), $INPUT.getCharPositionInLine()+1)));
           }
        | IF='if' '(' e1=expression ')' b1=body ('else' b2=body {$else=$b2.ast; })? {$ast.add(new IfElse($e1.ast, $b1.ast, $else, $IF.getLine(), $IF.getCharPositionInLine()+1)); }
        | RET='return' e1=expression ';' {$ast.add(new Return($e1.ast, $RET.getLine(), $RET.getCharPositionInLine()+1)); }
       ;

body returns [List<Statement> ast = new ArrayList<>()]:
    '{' (s1=statement {$ast.addAll($s1.ast); })* '}'
    | s1=statement {$ast.addAll($s1.ast); }
    ;

// Mejor antes los datos o la posicion?
expression returns [Expression ast]:
            INT_CONSTANT {$ast = new IntLiteral(LexerHelper.lexemeToInt($INT_CONSTANT.text), $INT_CONSTANT.getLine(), $INT_CONSTANT.getCharPositionInLine()+1); }
            | CHAR_CONSTANT {$ast = new CharLiteral(LexerHelper.lexemeToChar($CHAR_CONSTANT.text), $CHAR_CONSTANT.getLine(), $CHAR_CONSTANT.getCharPositionInLine()+1); }
            | REAL_CONSTANT {$ast = new NumberLiteral(LexerHelper.lexemeToReal($REAL_CONSTANT.text), $REAL_CONSTANT.getLine(), $REAL_CONSTANT.getCharPositionInLine()+1); }
            | ID {$ast = new Variable($ID.text, $ID.getLine(), $ID.getCharPositionInLine()+1); }
            | '('e1=expression')' {$ast=$e1.ast; }
            | e1=expression'['e2=expression']' {$ast = new ArrayAccess($e1.ast, $e2.ast, $e1.ast.getLine(), $e1.ast.getColumn()); }
            | e1=expression'.'ID {$ast = new FieldAccess($e1.ast, $ID.text, $e1.ast.getLine(), $e1.ast.getColumn()); }
            | ID '('es=expression_list?')' {$ast = new FunctionCall(new Variable($ID.text, $ID.getLine(), $ID.getCharPositionInLine()+1), $es.ast, $ID.getLine(), $ID.getCharPositionInLine()+1); }
            | INIT='(' e1=expression 'as' st=simple_type ')' {$ast = new Cast($st.ast, $e1.ast, $INIT.getLine(), $INIT.getCharPositionInLine()+1); }
            |'-' e1=expression {$ast = new UnaryMinus($e1.ast, $e1.ast.getLine(), $e1.ast.getColumn()); }
            | '!' e1=expression {$ast = new UnaryNot($e1.ast, $e1.ast.getLine(), $e1.ast.getColumn()); }
            | e1=expression OP=('*'|'/'|'%') e2=expression {$ast = new Arithmetic($e1.ast, $e2.ast, $OP.text, $e1.ast.getLine(), $e1.ast.getColumn()); }
            | e1=expression OP=('+'|'-') e2=expression {$ast = new Arithmetic($e1.ast, $e2.ast, $OP.text, $e1.ast.getLine(), $e1.ast.getColumn()); }
            | e1=expression OP=('>'|'>='|'<'|'<='|'!='|'==') e2=expression {$ast = new Comparison($e1.ast, $e2.ast, $OP.text, $e1.ast.getLine(), $e1.ast.getColumn()); }
            | e1=expression OP=('&&'|'||') e2=expression {$ast = new Logical($e1.ast, $e2.ast, $OP.text, $e1.ast.getLine(), $e1.ast.getColumn()); }
            ;

expression_list returns [List<Expression> ast = new ArrayList<>()]:
            e1=expression {$ast.add($e1.ast);}
            | e1=expression ',' list=expression_list {$ast.add($e1.ast); $ast.addAll($list.ast);}
            ;

// ----------------------------------------------------------------

// Tokens
INT_CONSTANT: '0'
             | [1-9]MACRO_DIGIT*
            ;

REAL_CONSTANT: REAL_PART MANTISA_PART?
                | INT_CONSTANT MANTISA_PART
               ;

CHAR_CONSTANT: '\''INSIDE_CHAR'\''
                 ;

ID: (MACRO_LETTER|'_')(MACRO_LETTER|MACRO_DIGIT)*
     ;

// Comentarios y basura a ignorar
ONE_LINE_COMMENT: '//'.*?('\n'|EOF) -> skip
                    ;

MORE_LINES_COMMENT: '/*'.*?'*/' -> skip
                    ;

TRASH: [\r\n\t ] -> skip
      ;

// Partes de una constante real
REAL_PART: (INT_CONSTANT'.'MACRO_DIGIT*)|('.'MACRO_DIGIT+)
          ;

MANTISA_PART: [eE][-+]?INT_CONSTANT
        ;

// Partes de una constante char
INSIDE_CHAR: (.*?)
            | ('\\'MACRO_DIGIT+)
            | '\\'[nt]
            ;

// Macros de uso general
MACRO_LETTER: [a-zA-Z_]
            ;
MACRO_DIGIT: [0-9];

