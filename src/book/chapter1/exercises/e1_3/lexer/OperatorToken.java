/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package book.chapter1.exercises.e1_3.lexer;

public class OperatorToken extends AbstractToken<Character> {
    private final char representation;
    
    public OperatorToken(char representation, String filename, int lineNumber) {
        super(filename, lineNumber);
        this.representation = representation;
    }
    
    @Override
    public Character getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return "OperatorToken{" + "representation=" + representation + ';' + super.toString() + '}';
    }
    

}
