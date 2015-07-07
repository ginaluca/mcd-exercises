
package book.chapter2.paragraph.p2_5;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Main {
    private static final String[] TEST_CASES = {
        "12345",                                    
        "a2_a_a",                                
        "+",         
        "_",                  
        ""      

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
