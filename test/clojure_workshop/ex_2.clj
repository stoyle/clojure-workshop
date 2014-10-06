(ns clojure-workshop.ex-2
  (:use [midje.sweet]))

(def _ 0)
(defn __ [& args] false)
(def ___ '())


(fact "A couple of things about midje"
      1 => 1
      (throw (Exception.)) => (throws Exception))

#_
(fact "working with seqs is pretty simple"
      ; how to get the head element of a seq
      (__ [1 2 3]) => 1
      ; how to get the tail element of a seq
      (__ [1 2 3]) => [2 3]
      ; What does rest of empty list do?
      (rest ()) => _
      ; What does next of empty list do?
      (next ()) => _
      ; What does drop produce?
      (drop 2 [1 2 3]) => ___
      ; Drop on a too small list?
      (drop 2 [1]) => ___
      ; Get the next of the first element
      (__ [[1 2][3 4]]) => [2]
      ; Get the first of the next element?
      (__ [[1 2] 3 4]) => 3)

#_
(fact "Some functions blow up if you dont use them correctly"
  (nth [1 2 3] _) => 2
  ; Fetch something out of range
  (nth [1 2 3] _) => (throws Exception)
  ; It is possible to give a default value when out of range, how do you do that?
  (nth [1 2 3] _ nil) => nil)

#_
(fact "Not all functions are meant to work with sequences, but you can always apply them.
      (Function to use is Clojure's version of .toString)"
      ; Which function to you use to create a string?
      (apply __ "1 2 3 " [4 " " 5 " " 6]) => "1 2 3 4 5 6")

#_
(fact "After a let (fn, defn), you can do side-effect"
      (let [a (atom 0)]
        ; Swapping atom state is pretty simple
        (__ a inc)
        (deref a) => 1))

#_
(fact "You want to 'do times' x with the swap! function"
      (let [a (atom 0)]
        (__ [_ 10]
          (swap! a inc))
        @a => 10))

#_
(fact "You want to do over a seq of functions the with swap! function"
      (let [a (atom 2)]
        ; Create a vector of two functions, first one that inc's the value and one that doubles
        ; the value. The funtions will be iterated over to produce the number 6 (* (+ 1 2) 2).
        ; This one may be a bit tricky. Check solution branch, or just skip to next, if you get stuck.
        (doseq [f [__ __]]
          (swap! a f))
        @a => 6))

#_
(fact "You want to inc until the correct value"
      (let [a (atom 0)]
        (__ (> 6 @a)
          (swap! a inc))
        @a => 6))

#_
(fact "Filter removes values from a sequence"
      ; Get the odd elements
      (filter __ [0 1 2 3 4 5 6 7 8 9]) => [1 3 5 7 9]
      ; Get the even elements
      (filter __ (take 10 (range))) => [0 2 4 6 8]
      ; Remove the nil which does not really have an identity
      (filter __ [1 2 3 nil 5]) => [1 2 3 5])

#_
(fact "remove is the opposite of filter"
      ; Remove odd
      (remove __ [0 1 2 3 4 5 6 7 8 9]) => [0 2 4 6 8]
      ; Remove even
      (remove __ (take 10 (range))) => [1 3 5 7 9]
      ; Remove all elements which does have an identity
      (remove __ [1 2 3 nil 5]) => [nil])

#_
(fact "Use map to transform/convert/map each element a list"
      ; Which function can be used to create a string from anything?
      (map __ [1 2 3]) => ["1" "2" "3"]
      ; Now increment each element, before converting it to a string. Use s function literal.
      (map __ [1 2 3]) => ["2" "3" "4"]
      ; The comp function is pretty cool at composing functions, try it!
      (map __ [1 2 3]) => ["2" "3" "4"]
      ; memfn can convert a java method to a function. Turn each element into uppercase words.
      (map __ ["a" "simple" "sentence"]) => ["A" "SIMPLE" "SENTENCE"])

#_
(fact "Map can be used with multiple collections. How can you simply get the index of value in a collection?"
      (map vector ___ [:a :b :c]) => [[0 :a] [1 :b] [2 :c]])

#_
(fact "With reduce you can do more"
      ; Function to add stuff together
      (reduce __ [1 2 3]) => 6
      ; Write out a funtion that take a staring list and conjoins resulting list
      (reduce __ [1] [2 3 4]) => [1 2 3 4]
      ; Can this be written simpler? Without a function literal? Just use the function directly...
      (reduce __ [1 2 3] [4 5 6]) => [1 2 3 4 5 6]
      ; How about creating a string on the fly with reduce? Try writing with funtion literal
      (reduce __ "" ["a " "simple " "sentence"]) => "A SIMPLE SENTENCE"
      ; Now, how about using comp for the same task?
      (reduce __ "" ["a " "simple " "sentence"]) => "A SIMPLE SENTENCE")
