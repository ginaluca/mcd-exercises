
Exercise 2.1
============

I made three versions of the program, two in Clojure and one in Java. Of the Clojure implementations, one (<code>mcd.chapter2.exercise2_1.tokenize</code>) is a first attempt at having a correct solution, without attention to performance. It parses http://introcs.cs.princeton.edu/java/data/dickens.txt in over 200 seconds.

The second Clojure implementation, to be found in <code>mcd.chapter2.exercise2_1.tokenize-fsa</code> uses a finite state automata to find matches. This causes a lower GC load than the previous code, because the state maintaned while walking the file is now just a Clojure symbol representing the current FSA node. This is a big improvement on the first implementation, which was creating many subsequences of the overall char sequence (the text to analyze), one per each character, in order to check if that character was the beginning of a 'abcabc' match. The second implementation analyzes the same input file in less than 10 seconds.

The Java implementation (<code>book.chapter2.exercises.e2_1.AbcAbcRecognizer</code>) uses a FSA as well, and analyzes the input file in less than half a second. I explain this difference in performance with the fact that the Java program produces almost no garbage. Another thing that might help is the smaller size of the call stack (less function calls) and the reduce size of the bytecode which helps processor caching.

Exercise 2.2
============

The classes which implement file loading one char at a time and one block at a time are <code>book.chapter2.exercises.e2_2.CharFileLoader</code> and <code>book.chapter2.exercises.e2_2.BlockFileLoader</code> respectively. There is a difference of two orders of magnitude between the time reqired to load a file char by char and the time to load the same file in blocks of 1024 bytes.

Exercise 2.3
============

No idea what the exercise means.

Exercise 2.4
============

Not in all languages replacing newlines with spaces preserves the semantic of a program. An example where the meaning of the program would change is the following Groovy script:

```
println '''first line
second line'''
```

which would print one line instead of two after such a transformation.

Apart form this inconvenient, the trick would prevent the lexical analyzer from associating position information (line and column number) to each token, therefore making debugging of programs harder.


