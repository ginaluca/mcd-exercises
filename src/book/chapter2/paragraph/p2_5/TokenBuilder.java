package book.chapter2.paragraph.p2_5;

import book.chapter1.exercises.e1_3.lexer.*;

public interface TokenBuilder {
    
    Token buildToken(char c, String filename, int lineNumber);
    
}
