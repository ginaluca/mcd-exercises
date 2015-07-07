
package book.chapter2.paragraph.p2_5;

public class DigitTokenBuilder implements TokenBuilder {

    @Override
    public Token buildToken(char c, String filename, int lineNumber) {
        if (c >= '0' && c <= '9') {
            return new DigitToken(c, filename, lineNumber);
        }
        return null;     
    }

}
