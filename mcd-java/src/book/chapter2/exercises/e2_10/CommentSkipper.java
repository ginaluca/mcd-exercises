
package book.chapter2.exercises.e2_10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CommentSkipper {
    private Reader reader;
    private int chint;
    
    public CommentSkipper(Reader reader) throws IOException {
        this.reader = reader;
        skipChar();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        CommentSkipper commentSkipper = new CommentSkipper(new FileReader(args[0]));
        commentSkipper.skipLayout();
        while(!commentSkipper.isEOI()) {
            System.out.print(commentSkipper.currentChar());            
            commentSkipper.skipChar();
        }
    } 
    
    public void skipChar() throws IOException {
        chint = reader.read();
    }
    
    public boolean isEOI() {
        return chint == -1;
    }
    
    public char currentChar() {
        return (char) chint;
    }
    
    public void skipLayout() throws IOException {
        while (isLayout(currentChar())) {
           skipChar();
        }
        while (isCommentStarter(currentChar())) {
            skipChar();
            while(!isCommentStopper(currentChar())) {
                if (isEOI()) {
                    return;
                }
                skipChar();
            }
            skipChar();
            while (isLayout(currentChar())) {
               skipChar();
            }
        }       
    }
    
    private boolean isLayout(char c) {
        return c == ' ' || c == '\t' || c == '\n';
    }
    
    private boolean isCommentStarter(char c) {
        return c == '[';
    }
    
    private boolean isCommentStopper(char c) {
        return c == ']';
    }
}
