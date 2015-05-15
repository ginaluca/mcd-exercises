
package exercises.e1_3.lexer;

public class OperatorTokenBuilder implements TokenBuilder {

    @Override
    public Token buildToken(char c, String filename, int lineNumber) {
        if (c == '+' || c == '*') {
            return new OperatorToken(c, filename, lineNumber);
        }
        return null;     
    }

}
