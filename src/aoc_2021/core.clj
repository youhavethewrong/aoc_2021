(ns aoc-2021.core
  (:gen-class)
  (:require [clojure.string :as str]
            [aoc-2021.day1 :as d1]
            [aoc-2021.day2 :as d2]))

(defn runner
  "Read input from file and run our function."
  [f m]
  (let [filename f
        content (slurp filename)
        lines (str/split content #"\n")]
    (cond
      (= m "d1a") (apply + (d1/increasing-depth-count lines))
      (= m "d1b") (apply + (d1/sliding-window-depth-count lines))
      (= m "d2a") (let [{hp :hp depth :depth} (d2/navigate-simple lines)]
                    (* hp depth))
      (= m "d2b") (let [{hp :hp depth :depth} (d2/navigate-with-aim lines)]
                    (* hp depth))
      )))

(defn -main
  [& args]
  (let [[input m & rest] args]
    (when (or (nil? input) (nil? m))
      (println "Usage: aoc_2021.jar <filename> <module>")
      (System/exit 1))
    (println "Result:" (runner input m))))
