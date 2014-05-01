(ns clojure-workshop-flatmap.ex-2
  (:use [midje.sweet])
  (:require [clojure.string :as str]))

(def _ 0)
(defn __ [& args] false)
(def ___ '())

(fact "A couple of things about midje"
      1 => 1
      (throw (Exception.)) => (throws Exception))

;; ffirst second
(fact "working with seqs is pretty simple"
      ; how to get the head element of a seq
      (first [1 2 3]) => 1
      ; how to get the tail element of a seq
      (rest [1 2 3]) => [2 3]
      ; What does rest of empty list do?
      (rest ()) => ()
      ; What does next of empty list do?
      (next ()) => nil
      ; What does drop produce?
      (drop 2 [1 2 3]) => [3]
      ; Drop on a too small list?
      (drop 2 [1]) => []
      ; Get the next of the first element
      (nfirst [[1 2][3 4]]) => [2]
      ; Get the first of the next element?
      (fnext [[1 2] 3 4]) => 3
      (fnext [[1 2] [3 4]]) => [3 4]
      (fnext []) => nil)

(fact "Some functions blow up if you dont use them correctly"
  (nth [1 2 3] 1) => 2
  (nth [1 2 3] 4) => (throws Exception)
  (nth [1 2 3] 4 nil) => nil)

(fact "Not all functions are meant to work with sequences, but you can always apply them"
      (apply map inc [[1 2 3]]) => [2 3 4]
      (apply str "1 2 3 " [4 " " 5 " " 6]) => "1 2 3 4 5 6")

(fact "After a let (fn, defn), you can do side-effect"
      (let [a (atom 0)]
        (swap! a inc)
        (deref a) => 1))

(fact "You want to 'do times' x with the swap! function"
      (let [a (atom 0)]
        (dotimes [x 10]
          (swap! a inc))
        @a => 10))

(fact "You want to do over a seq of functions the with swap! function"
      (let [a (atom 2)]
        (doseq [f [inc (partial * 2)]]
          (swap! a f))
        @a => 6))

(fact "You want to inc while not at the correct value"
      (let [a (atom 0)]
        (while (> 6 @a)
          (swap! a inc))
        @a => 6))

; map filter reduce

(fact "Filter removes values from a sequence"
      (filter odd? [0 1 2 3 4 5 6 7 8 9]) => [1 3 5 7 9]
      (filter even? (take 10 (range))) => [0 2 4 6 8]
      (filter identity [1 2 3 nil 5]) => [1 2 3 5])

(fact "Use map to transform each element a list"
      (map str [1 2 3]) => ["1" "2" "3"]
      (map #(str (inc %)) [1 2 3]) => ["2" "3" "4"]
      (map (memfn toUpperCase) ["a" "simple" "sentence"]) => ["A" "SIMPLE" "SENTENCE"])

(fact "Map can be used with multiple collections. How do you get the index of value in a collection?"
      (map vector (range) [:a :b :c]) => [[0 :a] [1 :b] [2 :c]])

(fact "With reduce you can do more"
      (reduce
       #((memfn toUpperCase) (str %1 " " %2)) ["a" "simple" "sentence"])
       => "A SIMPLE SENTENCE"
      (reduce
              #(conj %1 ((memfn toUpperCase) %2)) [] ["a" "simple" "sentence"])
       => ["A" "SIMPLE" "SENTENCE"])


