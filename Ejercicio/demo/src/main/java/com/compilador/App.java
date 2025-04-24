package com.compilador;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.gui.TreeViewer;

import javax.swing.*;
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
            System.out.println("\n=== ANÁLISIS SINTÁCTICO ===");
            System.out.println("Intentando analizar el archivo como programa...");

            // Intentar analizar como un programa general
            ParseTree tree = parser.programa(); // Asegúrate de que "programa" sea la regla en tu gramática
            
            System.out.println("\n✅ Análisis sintáctico completado sin errores.");
            System.out.println("Representación textual del árbol sintáctico:");
            System.out.println(tree.toStringTree(parser));
            
            // Mostrar estructura de la expresión
            System.out.println("\nEstructura de la expresión:");
            ExprVisitor visitor = new ExprVisitor();
            visitor.visit(tree);
            
            // Generar imagen del árbol sintáctico
            generarImagenArbolSintactico(tree, parser);

        } catch (ParseCancellationException e) {
            System.out.println("\n❌ " + e.getMessage());
            System.out.println("El archivo no pudo ser analizado como programa válido.");
        }
    }
    
    private static void generarImagenArbolSintactico(ParseTree tree, MiLenguajeParser parser) {
        // Usar ANTLR para generar una imagen del árbol
        try {
            // Mostrar el árbol en una ventana gráfica
            JFrame frame = new JFrame("Árbol Sintáctico");
            JPanel panel = new JPanel();
            
            TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
            viewer.setScale(1.5); // Escala para mejor visualización
            panel.add(viewer);
            
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            
            frame.add(scrollPane);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setVisible(true);
            
        } catch (Exception e) {
            System.out.println("❌ Error al generar la imagen del árbol: " + e.getMessage());
        }
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
    public Void visitPrograma(MiLenguajeParser.ProgramaContext ctx) {
        indent();
        System.out.println("Programa:");
        return visit(ctx.a());  // Visitamos la regla 'a'
    }

    @Override
    public Void visitConParentesis(MiLenguajeParser.ConParentesisContext ctx) {
        indent();
        System.out.println("Con paréntesis:");
        indentLevel++;
        visit(ctx.b()); // Visitamos 'b' dentro de 'a'
        indentLevel--;
        return null;
    }

    @Override
    public Void visitVacioA(MiLenguajeParser.VacioAContext ctx) {
        indent();
        System.out.println("Vacio A");
        return null;
    }

    @Override
    public Void visitPrimerHexa(MiLenguajeParser.PrimerHexaContext ctx) {
        indent();
        System.out.println("Primer Hexadecimal: " + ctx.HEXA().getText());
        visit(ctx.c());  // Visitamos 'c' después de 'b'
        return null;
    }

    @Override
    public Void visitHexaRecursiva(MiLenguajeParser.HexaRecursivaContext ctx) {
        indent();
        System.out.println("Hexadecimal Recursivo: " + ctx.HEXA().getText());
        visit(ctx.c());  // Recursión de 'c'
        return null;
    }

    @Override
    public Void visitVacioC(MiLenguajeParser.VacioCContext ctx) {
        indent();
        System.out.println("Vacio C");
        return null;
    }
}
