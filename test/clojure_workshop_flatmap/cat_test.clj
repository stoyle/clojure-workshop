(ns clojure-workshop-flatmap.cat-test
  (:use midje.sweet
        clojure-workshop-flatmap.cat)
  (:require [clojure.string :as str]))

(fact "Should number all lines"
      (number-lines {} "text") =>   (contains      "     1 text")
      (number-lines {} "en\nto") => (contains (str "     1 en\n"
                                                   "     2 to")))

(fact "Should number all lines and keep record of current line numbering"
      (number-lines {} "text") =>   [{:line-cnt 2}               "     1 text"]
      (number-lines {:line-cnt 2} "text") => [{:line-cnt 3}      "     2 text"]
      (number-lines {} "en\nto") => [{:line-cnt 3} (str          "     1 en\n"
                                                                 "     2 to")])