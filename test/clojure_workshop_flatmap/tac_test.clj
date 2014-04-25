(ns clojure-workshop-flatmap.tac-test
  (:import (java.io ByteArrayInputStream))
  (:use midje.sweet
        clojure-workshop-flatmap.tac))

(fact "Tac should work with newline separator"
      (tac "string" "\n" nil nil) => "string"
      (tac "string" "\n" true nil) => "string"
      (tac "string" "\n" true true) => "string"
      (tac "1\n2" "\n" nil nil) => "2\n1"
      (tac "1\n2" "\n" true nil) => "2\n1"
      (tac "1\n2" "\n" true true) => "2\n1")

(fact "Tac should work with string separator"
      (tac "string" "1" nil nil) => "string"
      (tac "a1b" "1" nil nil) => "b1a"
      (tac "a1b\na" "1" nil nil) => "b\na1a")

(fact "Tac should work with regex separator"
      (tac "string" "1" true nil) => "string"
      (tac "a1b" "1" true nil) => "b1a"
      (tac "a1b" "\\d" true nil) => "b\\da")

#_
(fact "Extra hard challenge. Make the example from the tac doc work"
      (tac "1ab2" "x\\|[^x]" true nil) => "2ba1")

