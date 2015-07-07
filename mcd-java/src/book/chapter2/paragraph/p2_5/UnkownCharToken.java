package book.chapter2.paragraph.p2_5;

import book.chapter1.exercises.e1_3.lexer.*;

public class UnkownCharToken extends AbstractToken<Character> {
    private final char representation;
    
    public UnkownCharToken(char representation, String filename, int lineNumber) {
        super(filename, lineNumber);
        this.representation = representation;
    }
    
    @Override
    public Character getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return "UnkownCharToken{" + "representation=" + representation + ';' + super.toString() + '}';
    }
    

}
