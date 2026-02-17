grammar TSmm;	

// Forzamos a que pare en el EOF
program: (variableDefinition | functionDefinition)* 'function' 'main' '(' ')' ':' 'void' '{' variableDefinition* sentence* '}' EOF
       ;

variableDefinition: 'let' idList ':' type ';'
                    ;

// Debe haber una función main en ultimo lugar
functionDefinition: 'function' ID '(' parameterList? ')' ':' type '{' variableDefinition* sentence* '}'
                    ;

 type: 'void'
       |'function'
       | '['INT_CONSTANT']' type
       | '['variableDefinition+']'
       | simpleType
       ;

simpleType: 'int'
    | 'number'
    | 'char'
    ;

sentence: ID '('expressionList?')' ';'
        | 'log' expressionList ';'
        | 'while' '(' expression ')' body
        | expression '=' expression ';'
        | 'input' expressionList ';'
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
            | ID '('expressionList?')'
            | '(' expression 'as' simpleType ')'
            |'-' expression
            | '!' expression
            | expression ('*'|'/'|'%') expression
            | expression ('+'|'-') expression
            | expression ('>'|'>='|'<'|'<='|'!='|'==') expression
            | expression ('&&'|'||') expression
            ;

expressionList: expression
            | expression ',' expressionList
            ;

idList: ID
    | ID ',' idList
    ;

parameterList: ID ':' simpleType
            | ID ':' simpleType ',' parameterList
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

