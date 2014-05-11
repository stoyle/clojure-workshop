(ns clojure-workshop-flatmap.ex-3
  (:use midje.sweet)
  (:require [clojure.walk :refer [macroexpand-all]]))

;; macroexpand and macroexpand-all are your friend
;; e.g. (macroexpand '(infix-s (1 + 2)))

(defmacro infix-s [form]
  form)

#_
(fact "Should handle simple infix"
      (infix-s (1 + 2)) => 3
      (macroexpand '(infix-s (1 + 2))) => '(+ 1 2))


(defmacro infix-v [& forms]
  forms)

#_
(fact "Should handle variable arity infix"
      (infix-v 1 + 2) => 3)


(defmacro infix-g
  ([& forms]
   forms))

#_
(fact "Should both variable and fixed arity"
      (infix-g 1 + 2) => 3
      (infix-g (1 + 2)) => 3)


(defmacro infix-r [form]
  form)

#_
(fact "Should handle recursive infix"
      (infix-r (1 + 2 + 3)) => 6)


(defmacro infix-rg [& form]
  form)

#_
(fact "Should handle variable arity recurisve infix"
      (infix-rg 1 + 2 + 3) => 6
      (infix-rg (1 + 2 + 3)) => 6
      (infix-rg (10 + (2 * 3) + (4 * 5))) => 36)


(defmacro infix-full [form]
  form)

#_
;; Bonus. I have not implemented this myself, and I believe is pretty hard. But if you are up for the challenge...
(fact "Should handle recursive infix with operator presendence and paren precedens"
      (infix-full (1 + 2 - (3 + 3))) => -3
      (infix-full (1 + 2 - 3 + 3)) => 3
      (infix-full (1 + 2 * 3)) => 6
      (infix-full ((1 + 2) * 3)) => 9)