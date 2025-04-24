grammar MiLenguaje;

// Regla inicial
programa : a EOF ;

// Regla 'a' con un paréntesis opcional y la recursión de b y c
a : PA b PC       # ConParentesis
  |               # VacioA
  ;

// Regla 'b' para manejar hexadecimales
b : HEXA c        # PrimerHexa
  ;

// Regla 'c' que permite una recursión en la forma de res - hexadecimales
c : RES HEXA c    # HexaRecursiva
  |               # VacioC
  ;

// Definición de los tokens
PA   : '(' ;
PC   : ')' ;
RES  : '-' ;
HEXA : '0x' [0-9A-F]+ ;

// Ignorar espacios, tabulaciones y saltos de línea
WS : [ \t\r\n]+ -> skip ;