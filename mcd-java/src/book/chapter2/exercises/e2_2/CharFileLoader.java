
package book.chapter2.exercises.e2_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CharFileLoader {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        InputStream is = new FileInputStream(args[0]);
        
        long t0 = System.currentTimeMillis();
        
        int acc = 0;
        int c = is.read();
        while(c != -1) {
            acc += c;
            c = is.read();
        }
        System.out.println("Time:"  + (System.currentTimeMillis() - t0));
        System.out.println("sum: " + acc);
    }
}
