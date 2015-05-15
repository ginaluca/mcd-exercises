/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercises.e1_3.lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class Lexer {
    private final Reader reader;
    private final TokenBuilder tokenBuilder = new DefaultTokenBuilder();
    private final String filename;
    private int lineNumber = 1;
    
    public Lexer(Reader reader) {
        this.reader = reader;
        this.filename = "UNKNOWN";
    }

    public Lexer(File source) throws FileNotFoundException {
        this.reader = new FileReader(source);
        this.filename = source.getName();
    }
    
    public Token nextToken() throws IOException {
        
        int chint;
        while (true) {
            chint = reader.read();
            if (chint == -1) {
                return new EofToken(filename, lineNumber);
            } else if (isLayoutChar(chint)) {
                lineNumber ++;
            } else {
                break;
            }
        }
        return tokenBuilder.buildToken((char) chint, filename, lineNumber);
       
    }
        
    private boolean isLayoutChar(int chint) {
        return chint == ' ' || chint == '\n' || chint == '\t';
    }
    
}
