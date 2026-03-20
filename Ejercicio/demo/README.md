# Ejercicios de Análisis Léxico con Expresiones Regulares

Este documento contiene una serie de ejercicios progresivos para aprender análisis léxico utilizando expresiones regulares y ANTLR4.

---

## Nivel 1: Fundamentos Básicos

### Ejercicio 1.1: Identificar Números Enteros
**Objetivo:** Reconocer números enteros positivos.


**Casos de prueba:**
```
123
0
999
42
```

**Casos que NO deben reconocer:**
```
12.5
-10
abc
```

---

### Ejercicio 1.2: Identificar Palabras Simples
**Objetivo:** Reconocer palabras formadas solo por letras.


**Casos de prueba:**
```
hola
Mundo
variable
```

**Casos que NO deben reconocer:**
```
var123
_nombre
123abc
```

---

### Ejercicio 1.3: Identificar Operadores Simples
**Objetivo:** Reconocer los operadores aritméticos básicos.


**Casos de prueba:**
```
+
-
*
/
```

**Archivo de prueba (ejercicio1_3.txt):**
```
5 + 3
10 - 2
4 * 7
9 / 3
```

---

## Nivel 2: Patrones Combinados

### Ejercicio 2.1: Números Enteros con Signo
**Objetivo:** Reconocer números enteros positivos y negativos.


**Casos de prueba:**
```
123
-45
+67
0
-999
```

**Casos que NO deben reconocer:**
```
12.5
--10
+-5
```

---

### Ejercicio 2.2: Números Decimales
**Objetivo:** Reconocer números con punto decimal.


**Casos de prueba:**
```
3.14
0.5
123.456
1.0
```

**Casos que NO deben reconocer:**
```
123
.5
5.
1.2.3
```

---

### Ejercicio 2.3: Identificadores Válidos
**Objetivo:** Reconocer identificadores que comienzan con letra o guión bajo, seguidos de letras, dígitos o guiones bajos.


**Casos de prueba:**
```
variable
_temp
contador1
MI_CONSTANTE
camelCase
snake_case
var_123_test
```

**Casos que NO deben reconocer:**
```
123abc
-variable
var-name
```


---

## Nivel 3: Tokens de Lenguajes de Programación

### Ejercicio 3.1: Palabras Reservadas
**Objetivo:** Distinguir entre palabras reservadas e identificadores.

**Expresión Regular (palabras reservadas):**
```
if | while | for | else | return | int | char | double
```

**Expresión Regular (identificador):**
```
[A-Za-z_][A-Za-z0-9_]*
```

**Casos de prueba:**
```
if          -> IF (palabra reservada)
while       -> WHILE (palabra reservada)
miVar       -> ID (identificador)
contador    -> ID (identificador)
return      -> RETURN (palabra reservada)
```

**Archivo de prueba (ejercicio3_1.txt):**
```
if (x > 0) {
    return x;
} else {
    while (y < 10) {
        y = y + 1;
    }
}
```

---

### Ejercicio 3.2: Operadores de Comparación
**Objetivo:** Reconocer operadores relacionales.

**Expresiones Regulares:**
```
==  -> EQL
!=  -> DISTINTO
>=  -> MAYOR_IGUAL
<=  -> MENOR_IGUAL
>   -> MAYOR
<   -> MENOR
```

**Casos de prueba:**
```
x == y
a != b
num >= 10
val <= 5
i > 0
j < 100
```

**Nota:** El orden importa (primero `>=` y `<=`, luego `>` y `<`)

---

### Ejercicio 3.3: Cadenas de Texto
**Objetivo:** Reconocer cadenas entre comillas dobles.


**Casos de prueba:**
```
"Hola Mundo"
"123"
"Variable: x"
""
"Texto con espacios"
```

**Casos especiales:**
```
"Comillas \"escapadas\""  -> Nivel avanzado
```

---

## Nivel 4: Patrones Avanzados

### Ejercicio 4.1: Caracteres Literales
**Objetivo:** Reconocer caracteres entre comillas simples.


**Casos de prueba:**
```
'a'
'Z'
'1'
'$'
'\n'   -> carácter de escape
'\t'   -> tabulación
```

**Casos que NO deben reconocer:**
```
''
'ab'
'
```

---

### Ejercicio 4.2: Comentarios de Línea
**Objetivo:** Reconocer y omitir comentarios de una línea.


**Casos de prueba:**
```
// Esto es un comentario
int x = 5;  // comentario al final de línea
// TODO: implementar función
```

**Acción:** `-> skip` (no generar token)

---

### Ejercicio 4.3: Comentarios de Bloque
**Objetivo:** Reconocer comentarios multilínea.

**Casos de prueba:**
```
/* Comentario simple */

/*
 * Comentario
 * de varias
 * líneas
 */

int x = 5; /* inline */ int y = 10;
```

**Acción:** `-> skip`

---

## Nivel 5: Casos Complejos

### Ejercicio 5.1: Números Científicos
**Objetivo:** Reconocer notación científica.

**Expresión Regular:** `[0-9]+\.?[0-9]*[eE][+-]?[0-9]+`

**Casos de prueba:**
```
1.5e10
3E-5
2.0e+3
1e10
```

---

### Ejercicio 5.2: Números Hexadecimales
**Objetivo:** Reconocer números en base 16.

**Expresión Regular:** `0[xX][0-9A-Fa-f]+`

**Casos de prueba:**
```
0xFF
0x1A2B
0X00
0xDEADBEEF
```

---

### Ejercicio 5.3: Operadores Compuestos
**Objetivo:** Reconocer operadores de asignación compuesta.

**Expresiones Regulares:**
```
+=  -> SUM_ASIG
-=  -> RES_ASIG
*=  -> MUL_ASIG
/=  -> DIV_ASIG
++  -> INC
--  -> DEC
```

**Casos de prueba:**
```
x += 5
y -= 3
z *= 2
contador++
--indice
```

**Nota:** El orden importa (primero `++`, `--`, `+=`, etc., luego `+`, `-`)

---

## Nivel 6: Proyecto Integrador

### Ejercicio 6.1: Analizador Léxico Completo
**Objetivo:** Crear un lexer completo para un mini-lenguaje.

**Archivo de entrada (programa.txt):**
```c
int main() {
    int x = 10;
    double y = 3.14;
    char c = 'A';

    // Calcular suma
    if (x > 5) {
        y += 2.5;
        x++;
    } else {
        while (x < 100) {
            x *= 2;
        }
    }

    /* Retornar resultado */
    return x + y;
}
```

**Tokens esperados:**
- Palabras reservadas: `int`, `double`, `char`, `if`, `else`, `while`, `return`
- Identificadores: `main`, `x`, `y`, `c`
- Números: `10`, `3.14`, `5`, `2.5`, `100`, `2`
- Caracteres: `'A'`
- Operadores: `=`, `>`, `+=`, `++`, `*=`, `+`
- Delimitadores: `(`, `)`, `{`, `}`, `;`
- Comentarios: (ignorados)

---

## Ejercicios de Desafío

### Desafío 1: Ambigüedad Léxica
**Problema:** ¿Qué reconoce primero: palabra reservada o identificador?

```
forvar
for
forma
```

**Solución:** Usar la regla más específica primero (palabra reservada antes que ID).

---

### Desafío 2: Cadenas con Escapes
**Objetivo:** Reconocer cadenas que contienen caracteres escapados.

**Expresión Regular:** `"([^"\\]|\\.)*"`

**Casos de prueba:**
```
"Hola \"Mundo\""
"Línea 1\nLínea 2"
"Ruta: C:\\Users\\nombre"
```

---

### Desafío 3: Detectar Errores Léxicos
**Objetivo:** Identificar tokens inválidos y reportar errores.

**Casos de error:**
```
@#$         -> Carácter inválido
'abc'       -> Carácter con más de un símbolo
12.34.56    -> Número mal formado
```

**Implementación:** Token `OTRO : .` para capturar errores.

---

## Instrucciones de Uso

### 1. Crear archivo de prueba
Crea un archivo `.txt` con el código a analizar:

```bash
echo "int x = 10;" > test.txt
```

### 2. Compilar el proyecto
```bash
mvn clean package
```

### 3. Ejecutar el análisis
```bash
java -jar target/demo-1.0-jar-with-dependencies.jar test.txt
```

### 4. Analizar resultados
El programa mostrará una tabla con:
- **TIPO**: Nombre del token
- **LEXEMA**: Texto reconocido
- **LÍNEA**: Número de línea
- **COLUMNA**: Posición en la línea

---

## Prioridad de Tokens

**Importante:** En ANTLR, el orden de las reglas léxicas importa:

1. Las reglas más específicas van primero
2. Las palabras reservadas antes que identificadores
3. Operadores compuestos antes que simples

**Ejemplo:**
```antlr
// ✅ CORRECTO
MAYOR_IGUAL : '>=' ;
MAYOR       : '>'  ;

// ❌ INCORRECTO (>= nunca se reconocería)
MAYOR       : '>'  ;
MAYOR_IGUAL : '>=' ;
```

---

## Soluciones

Las soluciones a estos ejercicios se encuentran implementadas en el archivo [MiLenguaje.g4](src/main/antlr4/com/compilador/MiLenguaje.g4).

Para modificar los tokens:
1. Edita el archivo `MiLenguaje.g4`
2. Recompila con `mvn clean package`
3. Prueba con tus archivos de entrada
