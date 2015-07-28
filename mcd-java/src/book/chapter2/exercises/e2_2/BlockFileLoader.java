
package book.chapter2.exercises.e2_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BlockFileLoader {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        InputStream is = new FileInputStream(args[0]);
        
        long t0 = System.currentTimeMillis();
        
        int acc = 0;
        byte buf[] = new byte[4096];
        int r = is.read(buf);
        while(r > 0) {
            for (int i = 0; i < r; i++) {
                acc += buf[i] & 0xFF;
            }
            r = is.read(buf);
        }
        System.out.println("Time:"  + (System.currentTimeMillis() - t0));
        System.out.println("sum: " + acc);
    }
}
