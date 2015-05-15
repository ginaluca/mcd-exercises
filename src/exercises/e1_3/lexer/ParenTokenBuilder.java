
package exercises.e1_3.lexer;

public class ParenTokenBuilder implements TokenBuilder {

    @Override
    public Token buildToken(char c, String filename, int lineNumber) {
        switch(c) {
            case '(':
                return new OpenParenToken(filename, lineNumber);
            case ')':
                return new ClosedParenToken(filename, lineNumber);
            default:
                return null;
        }
    }

}
