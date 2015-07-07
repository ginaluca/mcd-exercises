/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package book.chapter1.exercises.e1_3;

import book.chapter1.exercises.e1_6.context.ConstantFolder;
import book.chapter1.exercises.e1_8.context.LispCodeGenerator;
import book.chapter1.exercises.e1_3.lexer.EofToken;
import book.chapter1.exercises.e1_3.lexer.Token;
import book.chapter1.exercises.e1_3.lexer.Lexer;
import book.chapter1.exercises.e1_3.lexer.UnkownCharToken;
import book.chapter1.exercises.e1_3.parser.AstRoot;
import book.chapter1.exercises.e1_3.parser.Expression;
import book.chapter1.exercises.e1_3.parser.ParenExpression;
import book.chapter1.exercises.e1_3.parser.Parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Main {
    private static final String[] TEST_CASES = {
        "1",                                    //simple
        "(1+2)",                                // parens
        " ( 1\n *\t\t\t\t\t2 )\n\n\n",          // space chars
        "(1 + (2 + (3 + 4)))",                  // nesting
        "(1 + 2(",                              // wrong
        "(1 + 2)1",                             // tokens after end of expression

    };
    
    public static void main(String[] args) throws IOException {
        for (String s: TEST_CASES) {
            System.out.println("Source: BEGIN" );
            System.out.println(s);
            System.out.println("Source: END" );
            iterate(new Lexer(reader(s)));
            try {
                AstRoot root = new ConstantFolder().annotate(new Parser(new Lexer(reader(s))).parseExpression());
                render(root);
                System.out.println("Lisp code:");
                new LispCodeGenerator().generate(root, System.out);
                System.out.println("");
            } catch (Exception e) {
                System.out.println("Failed to parse with error: " + e.getMessage());
            }
            System.out.println("----------------");
        }
        
    }
        
    private static Reader reader(String string) {
        return new StringReader(string);
    }

    private static void iterate(Lexer lexer) throws IOException {
        System.out.println("Tokens:");
        int i = 1;
        while(true) {
            Token token = lexer.nextToken();
            System.out.printf("  %d. %s\n",i++, token.toString());
            if (token instanceof EofToken || token instanceof UnkownCharToken) {
                break;
            }
        }
    }

    private static void render(AstRoot parseExpression) {
        System.out.println("Parse tree:");
                    doRender(parseExpression.getExpression(), 2);
    }


    private static void doRender(Expression parseExpression, int indent) {
        System.out.printf("%s- %s\n", indentStr(indent), parseExpression.toString());
        if (parseExpression instanceof ParenExpression) {
            doRender(((ParenExpression) parseExpression).getLeft(), indent + 1);
            doRender(((ParenExpression) parseExpression).getRight(), indent + 1);
        }
    }
    
    private static String indentStr(int indent ) {
        StringBuilder v = new StringBuilder();
        for (int i = 0; i < indent ; i++) {
            v.append("    ");
        }
        return v.toString();
    }

}
