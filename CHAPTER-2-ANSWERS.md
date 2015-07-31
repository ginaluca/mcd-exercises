
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

Exercise 2.5
============

The description of the Algol 68 *format* given in the exercise is too concise, probably incomplete. The first example, <code>$3d$</code>, is followed by the more complicated <code>$n(2\*a)d$</code> without explaining where that <code>n</code> comes from. It then says that a *format* can contain function calls, but doesn't specify what kind of function you might find inside it. I guess arithmetic functions, since the example was about numbers. But then I'm confused by the statement that a function call might have formats as parameters: formats are started and ended by the same character <code>$</code>, how can they be nested without creating ambiguity?

Exercise 2.6
============

(a) <code>0\*10\*10*</code>

(b) <code>0\*(10\*10\*)\*</code>

Note 1: I might have reused the first expression to construct the second one like <code>(0\*10\*10\*)\*</code>, but that would have resulted in an ambiguous expression.

Note 2: after writing the second expression I wondered: does the <code>(10\*10\*)\*</code> describe a (potentially empty) sequence of sequences of <code>0</code> and <code>1</code>, all of which follow the pattern  <code>10\*10\*</code>? Or does it describe one of the possible productions of <code>10\*10\*</code>, repeated an arbitrary number of times (including zero times)? I'm sure that the first option is the right one, and therefore the expression given in (b) is a correct solution, but in order to provide some formal basis to this answer I need to go back to the grammar which is the origin of (b):

- <code>sequence_with_two_ones -> 10\*10\*</code>
- <code>even_sequence -> 0\* sequence_with_two_ones\*</code>

Since <code>even_sequence</code> is the start symbol of the grammar, in any derivation the second production rule is the first to be executed, generating a sentential form with a number of  <code>sequence_with_two_ones</code>. Each subsequent production step always involves the first rule, replacing an instance of <code>sequence_with_two_ones</code> with a (potentially novel) sequence conforming to <code>10\*10\*</code>.

Exercise 2.7
============

The pattern <code>.</code> usually excludes the new line because token don't cross line boundaries.

Exercise 2.8
============

<code>a?\*</code> is equivalent to <code>(a?)\*</code>, and it's not erroneous.

The following derivation:

1. <code>(a?)\*</code>
2. <code>(a?)(a?)</code>
3. <code>()(a?)</code> 
4. <code>()(a)</code> 

and this other derivation:

1. <code>(a?)\*</code>
2. <code>(a?)(a?)</code>
3. <code>(a?)()</code> 
4. <code>(a)()</code> 

Are different, but produce the same string. Therefore <code>a?\*</code> is ambiguous.