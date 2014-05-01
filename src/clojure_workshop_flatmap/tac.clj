(ns clojure-workshop-flatmap.tac
  (:gen-class)
  (:import (java.util.regex Pattern))
  (:require
    [clojure-workshop-flatmap.args :as args]
    [clojure.string :as str]))
;;
;; "You should implement 'tac'.
;;
;; The version of tac available from homebrew on mac has the following usage info:
;;
;; Usage: tac [OPTION]... [FILE]...
;; Write each FILE to standard output, last line first.
;; With no FILE, or when FILE is -, read standard input.
;;
;; Mandatory arguments to long options are mandatory for short options too.
;;   -b, --before             attach the separator before instead of after
;;   -r, --regex              interpret the separator as a regular expression
;;   -s, --separator=STRING   use STRING as the separator instead of newline
;;       --help     display this help and exit
;;       --version  output version information and exit
;;
;; GNU coreutils online help: <http://www.gnu.org/software/coreutils/>
;; Report tac translation bugs to <http://translationproject.org/team/>
;; For complete documentation, run: info coreutils 'tac invocation'
;;
;; The C implementation:
;; http://ftp-archive.freebsd.org/pub/FreeBSD-Archive/old-releases/i386/1.0-RELEASE/ports/textutils/src/tac.c
;;

(defn read-file [f]
  (slurp f))

(defn tac
  "Performs all the business logic. Converts content according to the tac spec."
  [content separator regex before]
  (let [pattern (if regex (re-pattern separator)
                          (Pattern/compile separator Pattern/LITERAL))]
    (str/join separator
              (reverse
                (str/split content pattern)))))

(defn tac-files
  "Takes a seq of files, gets their contents, and runs tac on each one."
  [files separator regex before]
  (map #(tac % separator regex before)
       (map read-file files)))

(defn read-in []
  (str/join
    \newline
    (loop [acc []]
      (if-let [line (read-line)]
        (recur (conj acc line))
        acc))))

(defn -main [& args]
  (println
    (str/join
      (let [{:keys [before regex separator files]} (args/parse-args args)]
        (cond
          files (tac-files files separator regex before)
          :else (tac (read-in) separator regex before))))))
