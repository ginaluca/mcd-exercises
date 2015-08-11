
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

<code>a\*\*</code> is equivalent to <code>(a\*)\*</code>, and it's not erroneous.

The following derivation:

1. <code>(a\*)\*</code>
2. <code>(a\*)(a\*)</code>
3. <code>()(a\*)</code> 
4. <code>()(a)</code> 

and this other derivation:

1. <code>(a\*)\*</code>
2. <code>(a\*)(a\*)</code>
3. <code>(a\*)()</code> 
4. <code>(a)()</code> 

Are different, but produce the same string. Therefore <code>a\*\*</code> is ambiguous.

Exercise 2.9
============

Note: I assume that there is a typo in the text of the exercise and the second production rule is actually 
<code>label -> letter (letgit_hyphen_string? letgit)?</code>
and not
<code>label -> letter ‘(‘ letgit_hyphen_string? letgit ‘)’?</code>

Original grammar:

1. <code>URL -> label | URL ‘.’ label</code>
2. <code>label -> letter (letgit_hyphen_string? letgit)?</code>
3. <code>letgit_hyphen_string -> letgit_hyphen | letgit_hyphen letgit_hyphen_string</code>
4. <code>letgit_hyphen -> letgit | ‘-‘</code>
5. <code>legit -> letter | digit</code>

Regular description:

1. <code>legit -> letter | digit</code>
2. <code>letgit_hyphen -> letgit | ‘-‘</code>
3. <code>letgit_hyphen_string -> letgit_hyphen+</code>
4. <code>label -> letter (letgit_hyphen_string? letgit)?</code>
5. <code>URL -> label | URL ‘.’ label</code>

Regular expression:

<code>(letter ((((letter | digit) | ‘-‘)+)? (letter | digit))?) | URL ‘.’ (letter ((((letter | digit) | ‘-‘)+)? (letter | digit))?)</code>

Exercise 2.10
=============

Original implementation of the <code>skip_layout_and_comment</code> function:

```java
void skip_layout_and_comment(void) {    while (is_layout(input_char)) {next_char();} 
    while (is_comment_starter(input_char)) {        next_char();        while (!is_comment_stopper(input_char)) {            if (is_end_of_input(input_char)) return; 
            next_char();        }        next_char();        while (is_layout(input_char)) {next_char();}    } 
}```

The function is designed to handle an input like below:
```
  [ comment ] [ comment ] rest of the program
```
assuming that <code>[</code> and <code>]</code> represent the start and end of a comment, respectively. The input is composed of an optional prefix of layout characters, followed by zero or more comments, followed by optional layout characters and finally by code which is neither layout or comment. The comment might be separated by zero, one or more layout characters.

The first <code>while</code> block skips the layout characters of the prefix until a comment or something else is found. Each iteration of the second <code>while</code> block skips a comment and the optional layout characters which separate it from the next comment or from the first non-layout, non-comment character. Its body contains:

1. a <code>next_char();</code> instruction which skips the comment-start character;
2. a <code>while</code> block which skips the content of the comment, comment-end excluded;
3. a <code>next_char();</code> instruction which skips the comment-end character;
4. a <code>while</code> block which skips the layout characters which separate the previous comment from what follows.

The exercise requires to modify the function to make it able to handle nested comments, like below:

```
  [ comment [ comment [ comment ] comment ] comment [ comment ] comment ] [ comment ] rest of the program
```

Let’s modify the body of the second <code>while</code> block, so that it will be able to skip a comment which might contain other comments, arbitrarily nested. 

Comments with nesting level > 0 can be separated by non layout characters, which are the text content of the enclosing comment. Skipping these comment requires a modified version of the original block to address this difference. The new code is also packaged as a function to allow recursive calls, following the nesting of comments.

```java
// Called with cursor on the opening comment starter.
// If it returns because of comment starter or end, on return the 
// cursor is still on that character.
void skip_nested_comment() {    next_char();    while (!is_comment_end(input_char))) {
        if (is_end_of_input(input_char)) return; 
        if (is_comment_starter(input_char)) {
            skip_nested_comment();
        } else {
            next_char();
        }
    } 
    next_char();
    while (!is_comment_starter(input_char) &&
           !is_comment_end(input_char)) {
        next_char();
        if (is_end_of_input(input_char)) return; 
    }}```

```java
void skip_layout_and_comment(void) {    while (is_layout(input_char)) {next_char();} 
    while (is_comment_starter(input_char)) {        next_char();        while (!is_comment_stopper(input_char)) {            if (is_end_of_input(input_char)) return; 
            if (is_comment_starter(input_char)) {
                skip_nested_comment();
            } else {
                next_char();
            }        }        next_char();        while (is_layout(input_char)) {next_char();}    } 
}```

Exercise 2.11
=============

If it is possible that `/` can appear outside a comment without being followed by `*`, then I don’t know how to implement the requested function, and I doubt that it is even possible. In such a scenario, an example input like
```
  program code /Program code
```  
would be legal. The problem with the implementation is that the function would need to read the `P` in order to understand that the preceding `/` is not the first character of a comment starter. Upon return, that `/` would be incorrectly skipped and therefore unavailable to the rest of the analyser.

Under the assumption that the circumstance above is not allowed, I first define two functions to recognise comment starter and comment stopper composed of the 2 characters `/` and `*`. The functions is defined as follows:

```java
/*
Assumes that a ’/‘ will be followed by a ‘*’.

Must be invoked with the cursor over the character which might be the ‘/‘.
If the comment starter is recognised, then on function return the cursor is
over the ‘*’. Otherwise, on function return the cursor is at the same position 
that it was at function entry.
*/
boolean recognise_comment_starter(void) {
    if (input_char != ‘/‘) {
        return false;
    }
    next_char();
    return true;
}```

```java
/*
Must be invoked with the cursor over the character which might be the ‘*‘.
If the comment stopper is recognised, then on function return the cursor is
over the ‘/’. Otherwise, on function return the cursor is one character further 
that it was at function entry.
*/
boolean recognise_comment_stopper(void) {
    if (input_char != ‘*‘) {
        next_char();
        return false;
    }
    next_char();
    if (input_char != ‘/‘) {
        return false;
    }
    return true;
}```

And this is how the original function gets modified:
```java
void skip_layout_and_comment(void) {    while (is_layout(input_char)) {next_char();} 
    while (recognise_comment(input_char)) {        next_char();        while (!recognise_comment_stopper(input_char)) {            if (is_end_of_input(input_char)) return; 
        }        next_char();        while (is_layout(input_char)) {next_char();}    } 
}```
