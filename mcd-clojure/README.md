
Exercise 2.1
------------

To execute the code:
- ```git clone``` the project and open a shell inside mcd-clojure
- launch ```lein repl``` (instructions to install Leiningen on http://leiningen.org)
- on the REPL, launch 
```lisp
(use 'mcd.exercise2_1)
```
- load the input file in memory with
```lisp
(def input (slurp "/path/to/input/file"))
```
- launch (and time) the lexycal analysis with
```lisp
(time (doall (tokenize input)))
```
The ```doall``` is needed to realize the lazy list obtained from ```tokenize```, thereby making sure that the computation actually happens within ```time```'s execution