package book.chapter1.exercises.e1_3.lexer;

public class EofToken extends AbstractToken<Void> {

    public EofToken(String filename, int lineNumber) {
        super(filename, lineNumber);
    }
    
    @Override
    public Void getRepresentation() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String toString() {
        return "EofToken{" + super.toString() + '}';
    }
    
}