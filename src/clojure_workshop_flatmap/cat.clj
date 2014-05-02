(ns clojure-workshop-flatmap.cat
  (:gen-class)
    (:import (java.util.regex Pattern))
    (:require
      [clojure-workshop-flatmap.args :as args]
      [clojure.string :as str]))

(defn read-file [f]
  (slurp f))

(defn format-line [line num]
  (format "%6d %s" num line))

(defn number-lines [state text]
  (let [current-cnt (:line-cnt state 1)
        lines (str/split-lines text)
        formatted-lines (map format-line lines
                             (iterate inc current-cnt))
        cnt (+ current-cnt (count lines))]
    [(assoc state :line-cnt cnt)
     (str/join \newline formatted-lines)]))

(defn number-non-blank-lines [state text]
  (let [lines (str/split-lines text)]
    [state text]))

(defn cat
  "Takes state (opts) and text to be formatted as params. Returns a vector of current state and text
   to be output.
   Should handle each option in the args/cli-options.
   :num - only number blank lines
   :num-non-blank - only number blank line (takes presendence if both present)"
  [state text]
  (cond
    (:num-non-blank-lines state) (number-non-blank-lines state text)
    (:num-all-lines state) (number-lines state text)
    :else [state text]))

(defn cat-files
  "Takes options and a set of filenames to handle. First reads each file to text, then converts text
  for each file over cat. Then converts each result vector to only the text (the second part of cat
  result. Finally joins these with a \newline."
  [opts files]
  (str/join \newline
    (map second
         (map (partial cat opts)
              (map read-file files)))))

(defn cat-in [opts]
  (loop [[state text] [opts nil]]
    (when text (println text))
    (when-let [line (read-line)]
      (recur (cat state line)))))

(defn -main [& args]
  (let [{files :files :as opts} (args/parse-args args)]
    (cond
      files (println (cat-files opts files))
      :else (cat-in opts))))