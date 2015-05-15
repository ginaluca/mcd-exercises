
package exercises.e1_3.parser;

import exercises.e1_3.lexer.ClosedParenToken;
import exercises.e1_3.lexer.DigitToken;
import exercises.e1_3.lexer.EofToken;
import exercises.e1_3.lexer.Lexer;
import exercises.e1_3.lexer.OpenParenToken;
import exercises.e1_3.lexer.OperatorToken;
import exercises.e1_3.lexer.Token;
import java.io.IOException;

public class Parser {
    private Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }  

    public AstRoot parseExpression() throws IOException {
        Expression expression = doParseExpression();
        Token lastToken = lexer.nextToken();
        if (!(lastToken instanceof EofToken)) {
            throw new IllegalStateException("expecting EOF, found " + lastToken);
        }
        return new AstRoot(expression);
    }

    private Expression doParseExpression() throws IOException {
        Token token = lexer.nextToken();
        if (token instanceof OpenParenToken) {
            Expression left = doParseExpression();
            OperatorToken operator = parseOperator();
            Expression right = doParseExpression();
            Token closing = lexer.nextToken();
            if (!(closing instanceof ClosedParenToken)) {
                        throw new IllegalStateException("expecting ), found " + closing);
            }
            return new ParenExpression(left, right, operator);
        } else if (token instanceof DigitToken) {
            return new DigitExpression((DigitToken) token);
        }
        throw new IllegalStateException("expecting ( or digit, found " + token);
    }
      
    private OperatorToken parseOperator() throws IOException {
        Token token = lexer.nextToken();
        if (token instanceof OperatorToken) {
            return (OperatorToken) token;
        }
        throw new IllegalStateException("expecting + or *, found " + token);
    }
}
