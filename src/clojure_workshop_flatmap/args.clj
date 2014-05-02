(ns clojure-workshop-flatmap.args
  (:require [clojure.string :as str]
            [clojure.tools.cli :as cli]))

(defn usage [options-summary]
  (->> ["usage: cat [-benstuv] [file ...]"
        ""
        "The cat utility reads files sequentially, writing them to the standard output.  The file operands are processed in command-line order.  If file is a single dash (`-') or"
        "absent, cat reads from the standard input.  If file is a UNIX domain socket, cat connects to it and then reads it until EOF.  This complements the UNIX domain binding capa-"
        "bility available in inetd(8)."
        ""
        "The options are as follows:"
        options-summary]
       (str/join \newline)))

(def cli-options
  ;; An option with a required argument
  [["-b" nil "Number the non-blank output lines, starting at 1." :id :num-non-blank]
   ["-n" nil "Number the output lines, starting at 1." :id :num-blank]
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

