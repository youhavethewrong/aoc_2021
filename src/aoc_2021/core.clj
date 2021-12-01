(ns aoc-2021.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn increasing-depth-count
  [input]
  (loop [c 0
         last nil
         chart input]
    (if (empty? chart)
      c
      (let [current (Integer/parseInt (first chart))]
        (recur (if (or (nil? last) (< current last))
                 c
                 (inc c))
               current
               (rest chart))))))

(defn runner
  "Read input from file and run our function."
  [f]
  (let [filename f
        content (slurp filename)
        lines (str/split content #"\n")
        count (increasing-depth-count lines)]
    count))

(defn -main
  [& args]
  (runner (first args)))
