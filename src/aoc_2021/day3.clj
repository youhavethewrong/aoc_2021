(ns aoc-2021.day3
  (:require [clojure.string :as str]))

(defn count-bits
  [diag]
  (reduce
   (fn [acc row]
     (map
      (fn [a r]
        (+ (Integer/parseInt (str a))
           (Integer/parseInt (str r))))
      acc
      row))
   diag))

(defn gamma-rate
  [diag]
  (let [bits (count-bits diag)
        c (count diag)]
    (Integer/parseInt
     (str/join
      (map
       (fn [b]
         (if (< b (/ c 2)) "0" "1"))
       bits))
     2)))

(defn epsilon-rate
  [diag]
  (let [bits (count-bits diag)
        c (count diag)]
    (Integer/parseInt
     (str/join
      (map
       (fn [b]
         (if (> b (/ c 2)) "0" "1"))
       bits))
     2)))


(defn get-all-nth
  [coll i]
  (map
   (fn [row]
     (Integer/parseInt (str (get row i))))
   coll))

(defn life-support-rating
  [diag op]
  (->
   (reduce
    (fn [acc i]
      (if (= 1 (count acc))
        acc
        (let [vals (get-all-nth acc i)
              s (apply + vals)
              target (if (op s (/ (count acc) 2)) "1" "0")]
          (filter #(= target (str (get % i))) acc))))
    diag
    (range (count (first diag))))
   first
   (Integer/parseInt 2)))

(defn oxygen-generator-rating
  [diag]
  (life-support-rating diag >=))

(defn co2-scrubber-rating
  [diag]
  (life-support-rating diag <))
