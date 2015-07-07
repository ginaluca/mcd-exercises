package book.chapter1.exercises.e1_3.lexer;

public class DigitToken extends AbstractToken<Short> {
    private final short representation;

    
    public DigitToken(char representation, String filename, int lineNumber) {
        super(filename, lineNumber);
        this.representation = Short.valueOf(new String(new char[] {representation}));
    }
    
    @Override
    public Short getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return "DigitToken{" + "representation=" + representation + ';' + super.toString() + '}';
    }
    

}
