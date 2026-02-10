grammar TSmm;	

program: INT_CONSTANT
       ;

TRASH: [\r\n\t ] -> skip
      ;
INT_CONSTANT: '0'
             | [1-9][0-9]*
            ;


