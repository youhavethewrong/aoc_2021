(ns aoc-2021.day2
  (:require [clojure.string :as str]))

(defn simple-nav
  [acc dir n]
  (cond
    (= dir "forward") (update acc :hp #(+ % n))
    (= dir "up") (update acc :depth #(- % n))
    (= dir "down") (update acc :depth #(+ % n))))

(defn aiming-nav
  [acc dir n]
     (cond
       (= dir "forward") (-> acc
                             (update :hp #(+ % n))
                             (update :depth #(+ % (* (:aim acc) n))))
       (= dir "up") (update acc :aim #(- % n))
       (= dir "down") (update acc :aim #(+ % n))))

(defn navigate
  [course f acc]
  (reduce
   (fn [acc line]
     (let [[dir sn] (str/split line #" ")
           n (Integer/parseInt sn)]
       (f acc dir n)))
   acc
   course))

(defn navigate-simple
  [course]
  (navigate course simple-nav {:hp 0 :depth 0}))

(defn navigate-with-aim
  [course]
  (navigate course aiming-nav {:hp 0 :depth 0 :aim 0}))
