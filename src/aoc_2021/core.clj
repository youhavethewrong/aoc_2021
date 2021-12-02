(ns aoc-2021.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn windowed-increases
  [coll n]
  (loop [acc '()
         last nil
         chart coll]
    (if (or (empty? chart)
            (< (count chart) n))
      acc
      (let [ints (map #(Integer/parseInt %) (take n chart))
            current (apply + ints)
            acc (conj acc (if (and (not (nil? last))
                                   (< last current)) 1 0))]
        (recur acc
               current
               (drop 1 chart))))))

(defn increasing-depth-count
  [input]
  (windowed-increases input 1))

(defn sliding-window-depth-count
  [input]
  (windowed-increases input 3)
)

(defn runner
  "Read input from file and run our function."
  [f]
  (let [filename f
        content (slurp filename)
        lines (str/split content #"\n")
        increases (sliding-window-depth-count lines)]
    (apply + increases)))

(defn -main
  [& args]
  (runner (first args)))
