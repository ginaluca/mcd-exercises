
package book.chapter1.exercises.e1_3.lexer;

import java.util.Arrays;
import java.util.List;

public class DefaultTokenBuilder implements TokenBuilder {
    private final List<TokenBuilder> BUILDERS = Arrays.asList(
            new ParenTokenBuilder(),
            new OperatorTokenBuilder(),
            new DigitTokenBuilder()
    );

    @Override
    public Token buildToken(char c, String filename, int lineNumber) {
        for (TokenBuilder tokenBuilder: BUILDERS) {
            Token token = tokenBuilder.buildToken(c, filename, lineNumber);
            if (token != null) {
                return token;
            }
        }   
        return new UnkownCharToken(c, filename, lineNumber);
    }

}
