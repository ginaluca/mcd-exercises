(ns mcd.exercise2_1
  (:use clojure.test))

(defn recognize-abcabc [v]
  (and (>= (.size v) 6)
         (= (v 0) \a)
         (= (v 1) \b)
         (= (v 2) \c)
         (= (v 3) \a)
         (= (v 4) \b)
         (= (v 5) \c)))

(defn negate [pred]
  (fn [arg] (not (pred arg))))

(defn tokenize [text]
  (let [vtext (vec text)]
    (filter (negate nil?)
            (map-indexed
             (fn [idx c]
               (when (recognize-abcabc (subvec vtext idx)) idx))
             vtext))))
