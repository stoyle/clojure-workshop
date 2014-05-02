(ns clojure-workshop-flatmap.cat
  (:gen-class)
    (:import (java.util.regex Pattern))
    (:require
      [clojure-workshop-flatmap.args :as args]
      [clojure.string :as str]))

(defn read-file [f]
  (slurp f))

(defn cat [opts text]
  [opts text])

(defn cat-files [opts files]
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