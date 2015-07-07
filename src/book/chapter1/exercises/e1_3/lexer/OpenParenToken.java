/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package book.chapter1.exercises.e1_3.lexer;

public class OpenParenToken extends AbstractToken<Void> {

    public OpenParenToken(String filename, int lineNumber) {
        super(filename, lineNumber);
    }

    @Override
    public Void getRepresentation() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String toString() {
        return "OpenParenToken{" + super.toString() + '}';
    }
    
    
}