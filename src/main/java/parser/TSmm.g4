grammar TSmm;	

program: INT_CONSTANT
        | REAL_CONSTANT
        | CHAR_CONSTANT
       ;

TRASH: [\r\n\t ] -> skip
      ;

INT_CONSTANT: '0'
             | [1-9]MACRO_DIGIT*
            ;

REAL_CONSTANT: REAL_PART MANTISA_PART?
                | INT_CONSTANT MANTISA_PART
               ;

REAL_PART: (INT_CONSTANT'.'MACRO_DIGIT*)|('.'MACRO_DIGIT+)
          ;

MANTISA_PART: [eE][-+]?INT_CONSTANT
        ;

ONE_LINE_COMMENT: '//'.*?('\n'|EOF) -> skip
                    ;

MORE_LINES_COMMENT: '/*'.*?'*/' -> skip
                    ;

ID: (MACRO_LETTER|'_')(MACRO_LETTER|MACRO_DIGIT)*
    ;

CHAR_CONSTANT: '\''INSIDE_CHAR'\''
                ;

INSIDE_CHAR: (.*?)
            | ('\\'MACRO_DIGIT+)
            | '\\'[nt]
            ;


// Macros de uso general
MACRO_LETTER: [a-zA-Z_]
            ;
MACRO_DIGIT: [0-9];