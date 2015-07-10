(ns mcd.exercise2_1
  (:use clojure.test))


                                        ; First version of tokenize

(defn recognize-abcabc
  "Recognizes the sequence 'abcabc' at the beginning of the char vector v."
  [v]
  (and (>= (.size v) 6)
         (= (v 0) \a)
         (= (v 1) \b)
         (= (v 2) \c)
         (= (v 3) \a)
         (= (v 4) \b)
         (= (v 5) \c)))

(defn negate
  "Returns a predicate which is the negation of pred, i.e. 
  (= (pred arg) ((negate pred) arg)) holds for any valid argument arg."
  [pred]
  (fn [arg] (not (pred arg))))

(defn tokenize
  "Returns the number of occurrences of 'abcabc' in text. text is a string."
  [text]
  (let [vtext (vec text)
        match-positions (filter (negate nil?)
                                (map-indexed
                                 (fn [idx c]
                                   (when (recognize-abcabc (subvec vtext idx)) idx))
                                 vtext))]
    (count match-positions)))


                                        ; Improved version of tokenize
                                       
(defn transition
  "Calculates the next state of the 'abcabc'-recognizing FSA 
  given the current state c and the input char c."
  [s, c]
  (cond
    (= c \a) (cond
               (= s :outside) :a1
               (= s :c1) :a2
               (= s :match) :a2
               :else :outside)
    (= c \b) (cond
               (= s :a1) :b1
               (= s :a2) :b2
               :else :outside)
    (= c \c) (cond
               (= s :b1) :c1
               (= s :b2) :match
               :else :outside)
    :else :outside))

(defn tokenize-fsa
  "Returns the number of occurrences of 'abcabc' in text. text is a string."
  [text]
  (let [vtext (vec text)
        acc (reduce
             (fn [acc c]
               (let [new-state (transition (acc :state) c)]
                 {:state new-state
                  :matches (if (= new-state :match)
                             (inc (acc :matches))
                             (acc :matches))}))
             {:state :outside
              :matches 0}
             vtext)]
    (acc :matches)))
