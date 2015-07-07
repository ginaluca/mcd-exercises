/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exercises.e1_3.lexer;

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
}
