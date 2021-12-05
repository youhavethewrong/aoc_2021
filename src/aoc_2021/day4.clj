(ns aoc-2021.day4
  (:require [clojure.string :as str]))

(def input (str/split (slurp "resources/day4-0.txt") #"\n"))

(defn parse-draws-and-boards
  [input]
  (loop [acc {:draws (str/split (first input) #",") :boards []}
         remaining (rest input)]
    (if (empty? remaining)
      acc
      (let [board (filter #(not (empty? %))
                          (str/split
                           (str/join " "
                                     (drop 1
                                           (take 6 remaining))) #" "))]
        (recur (update acc :boards #(conj % board))
               (drop 6 remaining))))))
