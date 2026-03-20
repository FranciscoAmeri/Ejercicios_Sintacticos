grammar MiLenguaje;

// Regla parser mínima (requerida por ANTLR)
programa : .*? EOF ;

fragment LETRA : [A-Za-z];
fragment DIGITO : [0-9];

// TOKENS 


SUM  : '+' ;
RES  : '-' ;
MUL  : '*' ;
DIV  : '/' ;
MOD  : '%' ;





ID : (LETRA | '_') (LETRA | DIGITO | '_')*;

INTEGER : DIGITO+;
DECIMAL : INTEGER'.'INTEGER;
CHARACTER: '\'' (~['\r\n] | '\\' .) '\'' ;

// Comentarios - Se ignoran durante el análisis
//COMENTARIO_LINEA : '//' ~[\r\n]*; sin ocultar
COMENTARIO_LINEA : '//' ~[\r\n]* -> skip;
COMENTARIO_BLOQUE : '/*' .*? '*/' -> skip;

WS : [ \r\n\t] -> skip ;
OTRO : . ;