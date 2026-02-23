grammar TSmm;	

// Forzamos a que pare en el EOF
program: (variable_definition | function_definition)* 'function' 'main' '(' ')' ':' 'void' '{' variable_definition* sentence* '}' EOF
       ;

variable_definition: 'let' id_list ':' type ';'
                    ;

// Debe haber una función main en ultimo lugar
function_definition: 'function' ID '(' parameter_list? ')' ':' type '{' variable_definition* sentence* '}'
                    ;

 type: 'void'
       |'function'
       | '['INT_CONSTANT']' type
       | '['variable_definition+']'
       | simple_type
       ;

simple_type: 'int'
    | 'number'
    | 'char'
    ;

sentence: ID '('expression_list?')' ';'
        | 'log' expression_list ';'
        | 'while' '(' expression ')' body
        | expression '=' expression ';'
        | 'input' expression_list ';'
        | 'if' '(' expression ')' body ('else' body)?
        | 'return' expression ';'
       ;

body: '{' sentence* '}'
    | sentence
    ;

expression: INT_CONSTANT
            | CHAR_CONSTANT
            | REAL_CONSTANT
            | ID
            | '('expression')'
            | expression'['expression']'
            | expression'.'ID
            | ID '('expression_list?')'
            | '(' expression 'as' simple_type ')'
            |'-' expression
            | '!' expression
            | expression ('*'|'/'|'%') expression
            | expression ('+'|'-') expression
            | expression ('>'|'>='|'<'|'<='|'!='|'==') expression
            | expression ('&&'|'||') expression
            ;

expression_list: expression
            | expression ',' expression_list
            ;

id_list: ID
    | ID ',' id_list
    ;

parameter_list: ID ':' simple_type
            | ID ':' simple_type ',' parameter_list
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

