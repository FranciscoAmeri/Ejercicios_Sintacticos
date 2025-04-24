package com.compilador;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.gui.TreeViewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java -jar demo-1.0-jar-with-dependencies.jar <archivo.txt>");
            System.exit(1);
        }

        try {
            // Cargar el archivo de entrada
            System.out.println("Analizando archivo: " + args[0]);
            
            CharStream inputLexico = CharStreams.fromFileName(args[0]);
realizarAnalisisLexico(inputLexico);

CharStream inputSintactico = CharStreams.fromFileName(args[0]);
realizarAnalisisSintactico(inputSintactico);
            
        } catch (IOException e) {
            System.err.println("❌ Error al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void realizarAnalisisLexico(CharStream input) {
        // Crear el lexer con manejo de errores personalizado
        MiLenguajeLexer lexer = new MiLenguajeLexer(input);
        
        // Configurar manejo de errores personalizado
        List<String> errores = new ArrayList<>();
        lexer.removeErrorListeners();
        lexer.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, 
                                  int line, int charPositionInLine, String msg, RecognitionException e) {
                String errorMsg = "ERROR LÉXICO en línea " + line + ":" + charPositionInLine + 
                                 " - " + msg;
                errores.add(errorMsg);
                throw new ParseCancellationException(errorMsg);
            }
        });
        
        try {
            // Obtener todos los tokens
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            tokens.fill();
            
            // Mostrar los tokens
            System.out.println("\n=== ANÁLISIS LÉXICO ===");
            System.out.printf("%-20s %-30s %-10s %-10s\n", "TIPO", "LEXEMA", "LÍNEA", "COLUMNA");
            System.out.println("-------------------------------------------------------------------");
            
            for (Token token : tokens.getTokens()) {
                if (token.getType() != Token.EOF) {
                    String tokenName = MiLenguajeLexer.VOCABULARY.getSymbolicName(token.getType());
                    System.out.printf("%-20s %-30s %-10d %-10d\n", 
                                     tokenName, token.getText(), token.getLine(), 
                                     token.getCharPositionInLine());
                }
            }
            
            System.out.println("\n✅ Análisis léxico completado sin errores.");
            
        } catch (ParseCancellationException e) {
            System.out.println("\n❌ " + e.getMessage());
            
            // Mostrar contexto del error (opcional)
            String[] lineas = input.toString().split("\n");
            if (e.getMessage().contains("línea")) {
                try {
                    int lineaError = Integer.parseInt(e.getMessage().split("línea ")[1].split(":")[0]);
                    int lineaInicio = Math.max(0, lineaError - 2);
                    int lineaFin = Math.min(lineas.length, lineaError + 1);
                    
                    System.out.println("\nContexto del error:");
                    for (int i = lineaInicio; i < lineaFin; i++) {
                        if (i + 1 == lineaError) {
                            System.out.println("→ " + (i + 1) + ": " + lineas[i]);
                        } else {
                            System.out.println("  " + (i + 1) + ": " + lineas[i]);
                        }
                    }
                } catch (Exception ex) {
                    // Si hay algún problema mostrando el contexto, simplemente lo omitimos
                }
            }
        }
    }
    
    private static void realizarAnalisisSintactico(CharStream input) {
        // Reiniciar el input para el análisis sintáctico
        MiLenguajeLexer lexer = new MiLenguajeLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiLenguajeParser parser = new MiLenguajeParser(tokens);
        
        // Configurar manejo de errores personalizado para el parser
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, 
                                  int line, int charPositionInLine, String msg, RecognitionException e) {
                String errorMsg = "ERROR SINTÁCTICO en línea " + line + ":" + charPositionInLine + 
                                 " - " + msg;
                throw new ParseCancellationException(errorMsg);
            }
        });
        
        try {
            System.out.println("\n=== ANÁLISIS SINTÁCTICO DE EXPRESIONES ===");
            System.out.println("Intentando analizar el archivo como expresión aritmética...");
            
            // Intentar analizar como expresión
            ParseTree tree = parser.expr();
            
            System.out.println("\n✅ Análisis sintáctico completado sin errores.");
            System.out.println("Representación textual del árbol sintáctico:");
            System.out.println(tree.toStringTree(parser));
            
            // Mostrar estructura de la expresión
            System.out.println("\nEstructura de la expresión:");
            ExprVisitor visitor = new ExprVisitor();
            visitor.visit(tree);
            
            // Mostrar árbol gráficamente
            mostrarArbolGrafico(tree, parser);
            
        } catch (ParseCancellationException e) {
            System.out.println("\n❌ " + e.getMessage());
            System.out.println("El archivo no pudo ser analizado como una expresión aritmética válida.");
            
            // Intento alternativo: analizar como programa general
            try {
                // Resetear el parser
                tokens.seek(0);
                parser.reset();
                
                System.out.println("\nIntentando analizar como programa general...");
                ParseTree tree = parser.programa();
                
                System.out.println("✅ Análisis como programa general completado sin errores.");
                
            } catch (Exception ex) {
                System.out.println("❌ ERROR: El archivo no cumple con la gramática definida.");
            }
        }
    }
    
    private static void mostrarArbolGrafico(ParseTree tree, MiLenguajeParser parser) {
        // Crear una ventana para mostrar el árbol
        JFrame frame = new JFrame("Árbol Sintáctico - Expresión Aritmética");
        JPanel panel = new JPanel();
        
        // Crear visualizador de árbol
        TreeViewer viewer = new TreeViewer(Arrays.asList(
                parser.getRuleNames()), tree);
        viewer.setScale(1.5); // Escalar para mejor visualización
        
        panel.add(viewer);
        
        // Añadir scroll para árboles grandes
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}

// Visitor para imprimir la estructura de la expresión
class ExprVisitor extends MiLenguajeBaseVisitor<Void> {
    private int indentLevel = 0;
    
    private void indent() {
        for (int i = 0; i < indentLevel; i++) {
            System.out.print("  ");
        }
    }
    
    @Override
    public Void visitAddition(MiLenguajeParser.AdditionContext ctx) {
        indent();
        System.out.println("Adición:");
        indentLevel++;
        
        indent();
        System.out.println("Lado izquierdo:");
        indentLevel++;
        visit(ctx.expr());
        indentLevel--;
        
        indent();
        System.out.println("Operador: +");
        
        indent();
        System.out.println("Lado derecho:");
        indentLevel++;
        visit(ctx.term());
        indentLevel--;
        
        indentLevel--;
        return null;
    }
    
    @Override
    public Void visitMultiplication(MiLenguajeParser.MultiplicationContext ctx) {
        indent();
        System.out.println("Multiplicación:");
        indentLevel++;
        
        indent();
        System.out.println("Lado izquierdo:");
        indentLevel++;
        visit(ctx.term());
        indentLevel--;
        
        indent();
        System.out.println("Operador: *");
        
        indent();
        System.out.println("Lado derecho:");
        indentLevel++;
        visit(ctx.factor());
        indentLevel--;
        
        indentLevel--;
        return null;
    }
    
    @Override
    public Void visitJustTerm(MiLenguajeParser.JustTermContext ctx) {
        return visit(ctx.term());
    }
    
    @Override
    public Void visitJustFactor(MiLenguajeParser.JustFactorContext ctx) {
        return visit(ctx.factor());
    }
    
    @Override
    public Void visitParentheses(MiLenguajeParser.ParenthesesContext ctx) {
        indent();
        System.out.println("Expresión entre paréntesis:");
        indentLevel++;
        visit(ctx.expr());
        indentLevel--;
        return null;
    }
    
    @Override
    public Void visitNumber(MiLenguajeParser.NumberContext ctx) {
        indent();
        System.out.println("Número entero: " + ctx.INTEGER().getText());
        return null;
    }
    
    @Override
    public Void visitDecimalNumber(MiLenguajeParser.DecimalNumberContext ctx) {
        indent();
        System.out.println("Número decimal: " + ctx.DECIMAL().getText());
        return null;
    }
    
    @Override
    public Void visitIdentifier(MiLenguajeParser.IdentifierContext ctx) {
        indent();
        System.out.println("Identificador: " + ctx.ID().getText());
        return null;
    }
}