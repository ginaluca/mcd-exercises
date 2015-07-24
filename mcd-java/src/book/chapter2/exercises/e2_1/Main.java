
package book.chapter2.exercises.e2_1;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class Main {
    private final static short STATE_EMPTY = 0;
    private final static short STATE_A1 = 1;
    private final static short STATE_B1 = 2;
    private final static short STATE_C1 = 3;
    private final static short STATE_A2 = 4;
    private final static short STATE_B2 = 5;
    private final static short STATE_MATCH = 6;
    private final static int[] FSA = {
        STATE_A1,
        STATE_EMPTY,
        STATE_EMPTY,
        STATE_A2,
        STATE_EMPTY,
        STATE_EMPTY,
        STATE_A2,
        STATE_EMPTY,
        
        STATE_EMPTY,
        STATE_B1,
        STATE_EMPTY,
        STATE_EMPTY,
        STATE_B2,
        STATE_EMPTY,
        STATE_EMPTY,
        STATE_EMPTY,
        
        STATE_EMPTY,
        STATE_EMPTY,
        STATE_C1,
        STATE_EMPTY,
        STATE_EMPTY,
        STATE_MATCH,
        STATE_EMPTY,
        STATE_EMPTY,
        
        STATE_EMPTY,
        STATE_EMPTY,
        STATE_EMPTY,
        STATE_EMPTY,
        STATE_EMPTY,
        STATE_EMPTY,
        STATE_EMPTY,
        STATE_EMPTY        
    };

    private static int charToIndex(int c) {
        switch (c) {
            case 97:
                return 0;
            case 98:
                return 1;
            case 99:
                return 2;
            default:
                return 3;
        }
    }
    
    public Main(Reader reader) throws IOException {
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        long t0 = System.currentTimeMillis();
        
        InputStream is = new FileInputStream(args[0]);
        byte buf[] = new byte[1024 * 4];
        int state = STATE_EMPTY;
        int occurrences = 0;
        
        int read = is.read(buf);
        while (read > 0) {
            
            for (int i = 0; i < read; i++) {
                int idxPart = charToIndex(buf[i]);
                int idx =  idxPart << 3;
                idx = idx | state;
                state = FSA[idx];
                if (state == STATE_MATCH) {
                    occurrences++;
                }
            }
            read = is.read(buf);
        }
        System.out.println(occurrences);
        System.out.println("time: " + (System.currentTimeMillis() - t0));
    } 
   
}
