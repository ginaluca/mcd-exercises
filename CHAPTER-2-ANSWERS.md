
Exercise 2.1
============

I made three versions of the program, two in Clojure and one in Java. Of the Clojure implementations, one (<code>mcd.chapter2.exercise2_1.tokenize<code>) is a first attempt at having a correct solution, without attention to performance. It parses http://introcs.cs.princeton.edu/java/data/dickens.txt in over 200 seconds.

The second Clojure implementation, to be found in <code>mcd.chapter2.exercise2_1.tokenize-fsa<code> uses a finite state automata to find matches. This causes a lower GC load than the previous code, because the state maintaned while walking the file is now just a Clojure symbol representing the current FSA node. This is a big improvement on the first implementation, which was creating many subsequences of the overall char sequence (the text to analyze), one per each character, in order to check if that character was the beginning of a 'abcabc' match. The second implementation analyzes the same input file in less than 10 seconds.

The Java implementation (<code>book.chapter2.exercises.e2_1.Main<code>) uses a FSA as well, and analyzes the input file in less than half a second. I explain this difference in performance with the fact that the Java program produces almost no garbage. Another thing that might help is the smaller size of the call stack (less function calls) and the reduce size of the bytecode which helps processor caching.