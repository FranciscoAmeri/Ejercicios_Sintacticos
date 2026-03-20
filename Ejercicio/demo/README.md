# Ejercicios de Análisis Léxico con Expresiones Regulares

Este documento contiene una serie de ejercicios progresivos para aprender análisis léxico utilizando expresiones regulares y ANTLR4.

---

## Nivel 1: Fundamentos Básicos

### Ejercicio 1.1: Identificar Números Enteros
**Objetivo:** Reconocer números enteros positivos.

**Expresión Regular:** `[0-9]+`

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

**Expresión Regular:** `[A-Za-z]+`

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

**Expresión Regular:** `[+\-*/]`

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

**Expresión Regular:** `[+-]?[0-9]+`

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

**Expresión Regular:** `[0-9]+\.[0-9]+`

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

**Expresión Regular:** `[A-Za-z_][A-Za-z0-9_]*`

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
