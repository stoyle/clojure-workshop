(ns clojure-workshop.ex-1
  (:require [clojure.test :refer :all]))

; Placeholders, do not touch.
(defn __ [& args] false)
(def ___ '())


(deftest very-basic-types
  ; Which number is the same as 1? :)
  (is (= 1 1))
  ; A string number is not = to the same number
  (is (not= "1" 1))
  ; A more lenient number comparison with ==
  (is (== 1 1.0))
  ; Only one thing is really true...
  (is (true? true))
  ; Now what is the concatenation of those Characters
  (is (= (str \a \b \c) "abc"))
  ; Use vector literal
  (is (= '(1 2 3) [1 2 3]))
  ; Use list literal
  (is (= [1 2 3] '(1 2 3)))
  ; Use list function
  (is (= [1 2 3] (list 1 2 3)))
  ; Use vector function
  (is (= [1 2 3] (vector 1 2 3))))


(deftest use-conjoin
  ; conjoin is a special function that works with most datastructures.
  ; It knows where the right place is to append an element.
  ; Use (doc conj) for info
  ; Oh, and by the way. 'are' creates a template (using a macro),
  ; for alle the following test form, which need to be in pairs in this case.
  (are [x y] (= x y)
       (conj [1] 2) [1 2]
       (conj [1 2] 3 4) [1 2 3 4]
       (conj '(2 1) 3 4) '(4 3 2 1)
       (conj #{2 1} 3 4) #{1 2 3 4}))


(deftest how-to-count-stuff
  (are [x y] (= x y)
       5 (count '(1 2 3 4 5))
       4 (count [1 2 3 4])
       10 (count (range 10))
       4 (count {:a 1, :b 2, :c 3, :d 4})
       8 (count "En banan")))


(deftest how-to-find-length-of-something
  ; First class functions are so cool. Put in the right function in let
  ; and all assertions should be correct.
  (let [f count]
    (are [x y] (= x y)
         (f '(1 2 3 3 1)) 5
         (f "Hello World") 11
         (f [[1 2] [3 4] [5 6]]) 3
         (f '(13)) 1
         (f '(:a :b :c)) 3)))


(deftest using-if
  ; Lots of things are truthy, i.e. is handled as true in e.g. if expressions.
  (are [x y] (= x y)
       (if (> 1 0)
         true
         false) true
       (if (> 3 2 1)
         true
         false) true

       (if (> 3 2 3 1)
         true
         false) false

       (if nil
         true
         false) false

       (if true
         true
         false) true

       (if false
         true
         false) false

       (if '(1)
         true
         false) true

       (if '()
         true
         false) true

       (if (Object.)
         true
         false) true))


(deftest dealing-with-lists
  (are [x y] (= x y)
       ; Should be able to use one simple function call to get result, what are their positions in the list?
       (first '(1 2 3 4))  1
       (last '(1 2 3 4 5)) 5
       ; Taking a part of a list is simple
       (take 2 '(1 2 3 4)) '(1 2)))


(deftest doing-math
  (are [x y] (= x y)
       ; Here you should try to use the correct mathematical function to get the expected result
       (+ 1 2 3 4) 10
       (- 4 3 2 1) -2
       (* 1 2 3 4) 24))

(deftest math-on-lists
  (are [x y] (= x y)
       ; Here you should try to apply the correct mathematical function to get the expected result
       (apply + [1 2 3 4]) 10
       (apply - [4 3 2 1]) -2
       (apply * [1 2 3 4]) 24))

(deftest define-a-function-that-checks-string-longer-than
  ; Write a function in the let form that that checks that the input String is longer than number input.
  (let [longer-than? #(> (count %1) %2)]
    (are [x y] (= x y)
         (longer-than? "long string" 5) true
         (longer-than? "short" 5) false
         (longer-than? nil 2) false)))

;; The following two tests are a sneap peak of the next section
(deftest how-to-filter-out-the-stuff-you-want
  (are [x y] (= x y)
       ; Filter is cool. Can you write or use a cool function to get correct result?
       (filter odd? '(1 2 3 4 5)) '(1 3 5)
       (filter even? '(1 2 3 4 5)) '(2 4)))


(deftest use-map-to-double-all-numbers-in-a-sequence
  ; Write a function in the let form that doubles its input
  (let [double (partial * 2)]
    (are [x y] (= x y)
         (map double '(1 2 3)) '(2 4 6)
         (map double '(5 10 15)) '(10 20 30))))
