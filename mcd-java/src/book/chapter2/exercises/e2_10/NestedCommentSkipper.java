
package book.chapter2.exercises.e2_10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class NestedCommentSkipper {
    private final Reader reader;
    private int chint;
    
    public NestedCommentSkipper(Reader reader) throws IOException {
        this.reader = reader;
        nextChar();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        NestedCommentSkipper commentSkipper = new NestedCommentSkipper(new FileReader(args[0]));
        commentSkipper.skipLayoutAndComments();
        while(!commentSkipper.isEOI()) {
            System.out.print(commentSkipper.currentChar());            
            commentSkipper.nextChar();
        }
    } 
    
    public void nextChar() throws IOException {
        chint = reader.read();
    }
    
    public boolean isEOI() {
        return chint == -1;
    }
    
    public char currentChar() {
        return (char) chint;
    }
    
    public void skipLayoutAndComments() throws IOException {
        while (isLayout()) {
           nextChar();
        }
        while (isCommentStarter()) {
            skipComment();
            
            while (isLayout()) {
               nextChar();
            }
        }       
    }
    
    /**
     * To execute when the cursor is on the comment starter,
     * brings you to the char after the closing comment stopper.
     */
    public void skipComment() throws IOException {
        nextChar();
        while(!isCommentStopper()) {
            if (isEOI()) {
                return;
            }
            if (isCommentStarter()) {
                skipComment();
            } else {
                nextChar();
            }
        }
        nextChar();
    }
    
    private boolean isLayout() {
        return currentChar() == ' ' || currentChar() == '\t' || currentChar() == '\n';  
    }
    
    private boolean isCommentStarter() {
        return currentChar() == '[';
    }
    
    private boolean isCommentStopper() {
        return currentChar() == ']';
    }
}
