(ns clojure-workshop.ex-2
  (:require [clojure.string :as s]
            [midje.sweet :refer :all]))

; Placeholders, do not touch.
(def _ 0)
(def __ (fn [& _] false))
(def ___ '())


(fact "A couple of things about midje"
      1 => 1
      (throw (Exception.)) => (throws Exception))


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
      (fnext [[1 2] 3 4]) => 3)


(fact "Some functions blow up if you dont use them correctly"
  (nth [1 2 3] 1) => 2
  ; Fetch something out of range
  (nth [1 2 3] 3) => (throws Exception)
  ; It is possible to give a default value when out of range, how do you do that?
  (nth [1 2 3] 3 nil) => nil)


(fact "Not all functions are meant to work with sequences, but you can always apply them.
      (Function to use is Clojure's version of .toString)"
      ; Which function to you use to create a string?
      (apply str "1 2 3 " [4 " " 5 " " 6]) => "1 2 3 4 5 6")


(fact "After a let (fn, defn), you can do side-effect"
      (let [a (atom 0)]
        ; Swapping atom state is pretty simple
        (swap! a inc)
        (deref a) => 1))


(fact "You want to 'do times' x with the swap! function"
      (let [a (atom 0)]
        (dotimes [x 10]
          (swap! a inc))
        @a => 10))


(fact "You want to do over a seq of functions the with swap! function"
      (let [a (atom 2)]
        ; Create a vector of two functions, first one that inc's the value and one that doubles
        ; the value. The funtions will be iterated over to produce the number 6 (* (+ 1 2) 2).
        ; This one may be a bit tricky. Check solution branch, or just skip to next, if you get stuck.
        (doseq [f [inc (partial * 2)]]
          (swap! a f))
        @a => 6))


(fact "You want to inc until the correct value"
      (let [a (atom 0)]
        (while (> 6 @a)
          (swap! a inc))
        @a => 6))


(fact "Filter removes values from a sequence"
      ; Get the positive elements
      (filter pos? [-2 8 -10 3]) => [8 3]
      ; Get the negative elements, from the 100 first of an infinite list.
      (filter neg? (take 100 (iterate inc -5))) => [-5 -4 -3 -2 -1]
      ; Remove the nil which does not really have an identity
      (filter identity [1 2 3 nil 5]) => [1 2 3 5])


(fact "remove is the opposite of filter"
      ; Remove odd
      (remove odd? [0 1 2 3 4 5 6 7 8 9]) => [0 2 4 6 8]
      ; Remove even
      (remove even? (take 10 (range))) => [1 3 5 7 9]
      ; Remove all elements which does have an identity
      (remove identity [1 2 3 nil 5]) => [nil])


(fact "Java interop is pretty simple"
      ; Java methods can be called as if they are functions in forms, e.g. by using . before method name. Convert to upper case.
      (.toUpperCase "first java string") => "FIRST JAVA STRING"
      ; Of course it is more idiomatic using the version from clojure.string (imported as s, e.g. s/capitalize)
      (s/upper-case "second java string") => "SECOND JAVA STRING"
      ; Clojure version is more generic, because it is not bound to String. Accepts any CharacterSequence.
      ; Creating java object is simple using the new special form.
      (s/upper-case (new StringBuilder "third java string")) => "THIRD JAVA STRING"

      ; What was the method which determines character at position. (hint first __ is method name (you know...the "char at" method), and next _ is the position in the string)
      (.charAt "a java string" 2) => \j

      ; Calling static methods is done with '/'. Format strings with the static format method (on java.lang.String).
      (String/format "Calling Java methods using %s is not that %s" (into-array ["varargs" "cool"])) => "Calling Java methods using varargs is not that cool"
      ; What is the clojure core function which formats strings?
      (format "%s uses %s, but is a lot %s" "format" "String.format" "nicer") => "format uses String.format, but is a lot nicer")


(fact "Use map to transform/convert/map each element a list"
      ; Which clojure function can be used to create a string from anything?
      (map str [1 2 3]) => ["1" "2" "3"]
      ; Now increment each element, before converting it to a string. Use a function literal.
      (map #(str (inc %)) [1 2 3]) => ["2" "3" "4"]
      ; The comp function is pretty cool at composing functions, try it!
      (map (comp str inc) [1 2 3]) => ["2" "3" "4"]

      ; And now for some more java interop
      ; A java method is not a function, and cannot be passed as one. Convert .toUpperCase, but wrap in function.
      (map #(.toUpperCase %) ["a" "simple" "sentence"]) => ["A" "SIMPLE" "SENTENCE"]
      ; memfn can convert a java method to a function. Turn each element into uppercase words.
      (map (memfn toUpperCase) ["a" "simple" "sentence"]) => ["A" "SIMPLE" "SENTENCE"])


(fact "Map can be used with multiple collections. How can you simply get the index of value in a collection?"
      ; In this case you can simply use an infinite sequence as first collection
      (map vector (range) [:a :b :c]) => [[0 :a] [1 :b] [2 :c]])


(fact "With reduce you can do more"
      ; Function to add stuff together
      (reduce + [1 2 3]) => 6
      ; Write out a funtion that take a starting list and conjoins resulting list
      (reduce (fn [r x] (conj r x)) [1] [2 3 4]) => [1 2 3 4]
      ; Can this be written simpler? Without a function literal? Maybe just use the function directly...
      (reduce conj [1 2 3] [4 5 6]) => [1 2 3 4 5 6]
      ; How about creating a string on the fly with reduce? Try writing with funtion literal, using the java .toUpperCase method
      (reduce #((memfn toUpperCase) (str %1 %2)) "" ["a " "simple " "sentence"]) => "A SIMPLE SENTENCE"
      ; Now, how about using comp for the same task? Composing toghether the function which creates strings and the .toUpperCase method
      (reduce (comp (memfn toUpperCase) str) "" ["a " "simple " "sentence"]) => "A SIMPLE SENTENCE")


(fact "Threading is a nice way of breaking up operations, -> handles singular values, inserts as the first element"
      ;; First inc(rement), and then convert to str(ing)
      (str (inc 1)) => "2"

      ;; The same, but now with threading
      (-> 1 inc str) => "2")


(fact "Threading is a nice way of breaking up operations, ->> handles collection values, inserts as the last element"
      ;; First inc(rement), and then convert to str(ing)
      (map str (map inc [1 2 3])) => ["2" "3" "4"]

      ;; The same, but now with threading
      (->> [1 2 3]
           (map inc)
           (map str)) => ["2" "3" "4"])
