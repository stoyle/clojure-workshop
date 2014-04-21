(ns clojure-workshop-flatmap.args
  (:require [clojure.string :as str]
            [clojure.tools.cli :as cli]))

(defn usage [options-summary]
  (->> ["Usage: tac [OPTION]... [FILE]..."
        "Write each FILE to standard output, last line first"
        "With no FILE, or when FILE is -, read standard input."
        ""
        "Mandatory arguments to long options are mandatory for short options too."
        options-summary]
       (str/join \newline)))

(def cli-options
  ;; An option with a required argument
  [["-b" "--before before" "attach the separator before instead of after"]
   ["-r" "--regex" "interpret the separator as a regular expression"]
   ["-s" "--separator separator" "use separator as the separator instead of newline"
    :default "\n"]
   ;; A boolean option defaulting to nil
   [nil "--help" "display this help and exit"]
   [nil "--version" "output version information and exit"]])

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (str/join \newline errors)))

(defn parse-args [args]
  (let [{:keys [options arguments errors summary]} (cli/parse-opts args cli-options)]
    ;; Handle help and error conditions
    (cond
      (:help options) (exit 0 (usage summary))
      (:version options) (exit 0 "0.1.0-SNAPSHOT")
      errors (exit 1 (error-msg errors))
      :else (assoc options :files arguments))))

