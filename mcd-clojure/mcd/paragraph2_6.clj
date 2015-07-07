(ns mcd.paragraph2_6
  (:use clojure.test))

(defn e-move [dotted-item]
  (let [fchar (first dotted-item)]
        (if fchar
          (conj (e-move (rest dotted-item)) fchar)
          (list))))

(defn char-after-closed-paren [chars]
  (let [char-after-closed-paren-recur
        (fn [chars level]
          (cond
            (and (= (first chars) \)) (= level 0))
            (second chars)
            (and (= (first chars) \)) (> level 0))
            (char-after-closed-paren-recur (rest chars) (dec level))
            (= (first chars) \()
            (char-after-closed-paren-recur (rest chars) (inc level))
            :else (char-after-closed-paren-recur (rest chars) level)))]
    (char-after-closed-paren-recur chars 0)))


