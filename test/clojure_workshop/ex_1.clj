(ns clojure-workshop.ex-1
  (:require [clojure.test :refer :all]))

; Placeholders, do not touch.
(defn __ [& args] false)
(def ___ '())

#_
(deftest very-basic-types
  ; Which number is the same as 1? :)
  (is (= 1 ___))
  ; A string number is not = to the same number
  (is (__ "1" 1))
  ; A more lenient number comparison with ==
  (is (__ 1 1.0))
  ; Only one thing is really true...
  (is (true? ___))
  ; Now what is the concatenation of those Characters
  (is (= (str \a \b \c) ___))
  ; Use vector literal
  (is (= '(1 2 3) ___))
  ; Use list literal with a quote '
  (is (= [1 2 3] ___))
  ; Use list function
  (is (= [1 2 3] ___))
  ; Use vector function
  (is (= [1 2 3] ___)))

#_
(deftest use-conjoin
  ; conjoin is a special function that works with most datastructures.
  ; It knows where the right place is to append an element.
  ; Use (doc conj) for info
  ; Oh, and by the way. 'are' creates a template (using a macro),
  ; for alle the following test form, which need to be in pairs in this case.
  (are [x y] (= x y)
       (conj [1] 2) ___
       (conj [1 2] 3 4) ___
       (conj '(2 1) 3 4) ___
       (conj #{2 1} 3 4) ___))

#_
(deftest how-to-count-stuff
  (are [x y] (= x y)
       ___ (count '(1 2 3 4 5))
       ___ (count [1 2 3 4])
       ___ (count (range 10))
       ___ (count {:a 1, :b 2, :c 3, :d 4})
       ___ (count "En banan")))

#_
(deftest how-to-find-length-of-something
  ; First class functions are so cool. Put in the right function in let
  ; and all assertions should be correct.
  (let [f __]
    (are [x y] (= x y)
         (f '(1 2 3 3 1)) 5
         (f "Hello World") 11
         (f [[1 2] [3 4] [5 6]]) 3
         (f '(13)) 1
         (f '(:a :b :c)) 3)))

#_
(deftest using-if
  ; Lots of things are truthy, i.e. is handled as true in e.g. if expressions.
  (are [x y] (= x y)
       (if (> 1 0)
         true
         false) ___
       (if (> 3 2 1)
         true
         false) ___

       (if (> 3 2 3 1)
         true
         false) ___

       (if nil
         true
         false) ___

       (if true
         true
         false) ___

       (if false
         true
         false) ___

       (if '(1)
         true
         false) ___

       (if '()
         true
         false) ___

       (if (Object.)
         true
         false) ___))

#_
(deftest dealing-with-lists
  (are [x y] (= x y)
       ; Should be able to use one simple function call to get result, what are their positions in the list?
       (__ '(1 2 3 4))  1
       (__ '(1 2 3 4 5)) 5
       ; Taking a part of a list is simple
       (__ 2 '(1 2 3 4)) '(1 2)))

#_
(deftest doing-math
  (are [x y] (= x y)
       ; Here you should try to use the correct mathematical function to get the expected result
       (__ 1 2 3 4) 10
       (__ 4 3 2 1) -2
       (__ 1 2 3 4) 24))

#_
(deftest math-on-lists
  (are [x y] (= x y)
       ; Here you should try to apply the correct mathematical function to get the expected result
       (__ + [1 2 3 4]) 10
       (__ - [4 3 2 1]) -2
       (__ * [1 2 3 4]) 24))

#_
(deftest define-a-function-that-checks-string-longer-than
  ; Write a function in the let form that that checks that the input String is longer than number input.
  (let [longer-than? __]
    (are [x y] (= x y)
         (longer-than? "long string" 5) true
         (longer-than? "short" 5) false
         (longer-than? nil 2) false)))


;; The following two tests are a sneak peek of the next section
#_
(deftest how-to-filter-out-the-stuff-you-want
  (are [x y] (= x y)
       ; Filter is cool. Can you write or use a cool function to get correct result?
       (filter __ '(1 2 3 4 5)) '(1 3 5)
       (filter __ '(1 2 3 4 5)) '(2 4)))

#_
(deftest use-map-to-double-all-numbers-in-a-sequence
  ; Write a function in the let form that doubles its input
  (let [dbl __]
    (are [x y] (= x y)
         (map dbl '(1 2 3)) '(2 4 6)
         (map dbl '(5 10 15)) '(10 20 30))))
