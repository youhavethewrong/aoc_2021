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

(defn sliding-window-depth-count
  [input]
  (loop [s 0
         last nil
         chart input]
    (if (or (empty? chart)
            (< (count chart) 3))
      s
      (let [[a b c] (map #(Integer/parseInt %) (take 3 chart))
            current (+ a b c)
            next (if (and (not (nil? last))
                          (< last current))
                   (inc s)
                   s)]
        (recur next
               current
               (drop 1 chart))))))

(defn runner
  "Read input from file and run our function."
  [f]
  (let [filename f
        content (slurp filename)
        lines (str/split content #"\n")
        count (sliding-window-depth-count lines)]
    count))

(defn -main
  [& args]
  (runner (first args)))
