(ns clojure-workshop.args
  (:require [clojure.string :as str]
            [clojure.tools.cli :as cli]))

(defn usage [options-summary]
  (->> ["usage: cat [-bnh] [file ...]"
        ""
        "The cat utility reads files sequentially, writing them to the standard output.  The file operands are processed in command-line order.  If file is "
        "absent, cat reads from the standard input."
        ""
        "The options are as follows:"
        options-summary]
       (str/join \newline)))

(def cli-options
  ;; An option with a required argument
  [["-b" nil "Number the non-blank output lines, starting at 1." :id :num-non-blank-lines]
   ["-n" nil "Number the output lines, starting at 1." :id :num-all-lines]
   ["-h" "--help" "Display usage help"]])

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
      errors (exit 1 (error-msg errors))
      :else (assoc options :files (seq arguments)))))

