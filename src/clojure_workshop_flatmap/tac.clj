(ns clojure-workshop-flatmap.tac
  (:gen-class)
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
;; "

(defn read-file [f]
  (str/split-lines (slurp f)))

(defn tac-file [f separator regex before]
  (str/join \newline
            (reverse (str/split (str/join \newline (read-file f))
                                (re-pattern separator)))))

(defn run-tac [before regex separator files]
  (doseq [f files]
    (println
      (tac-file f separator regex before))))

(defn -main [& args]
  (let [{:keys [before regex separator files]} (args/parse-args args)]
    (cond
      files (run-tac before regex separator files)
      :else (println "you must specify files"))))