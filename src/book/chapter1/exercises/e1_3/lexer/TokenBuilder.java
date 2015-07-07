package book.chapter1.exercises.e1_3.lexer;

public interface TokenBuilder {
    
    Token buildToken(char c, String filename, int lineNumber);
    
}
