
package book.chapter1.exercises.e1_3.lexer;

public class DigitTokenBuilder implements TokenBuilder {

    @Override
    public Token buildToken(char c, String filename, int lineNumber) {
        if (c >= '0' && c <= '9') {
            return new DigitToken(c, filename, lineNumber);
        }
        return null;     
    }

}
