
package book.chapter1.exercises.e1_3.parser;

import book.chapter1.exercises.e1_3.lexer.DigitToken;

public class DigitExpression extends AbstractExpression {
    private DigitToken digit;

    public DigitExpression(DigitToken digit) {
        this.digit = digit;
    }

    public DigitToken getDigit() {
        return digit;
    }

    @Override
    public String toString() {
        return "DigitExpression{" + "digit=" + digit + "; annotations=" + annotations + '}';
    }
    
    
}
