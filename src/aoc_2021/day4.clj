(ns aoc-2021.day4
  (:require [clojure.string :as str]))

(defn parse-draws-and-boards
  [input]
  (loop [acc {:draws (str/split (first input) #",") :boards []}
         remaining (rest input)]
    (if (empty? remaining)
      acc
      (let [board (map-indexed (fn [i x] {:i i :v x :marked false})
                   (filter #(not (empty? %))
                    (str/split (str/join " " (drop 1 (take 6 remaining))) #" ")))]
        (recur (update acc :boards #(conj % board))
               (drop 6 remaining))))))

(defn check-horizontal
  [board]
  (first
   (filter #(every? :marked %)
           (partition 5 board))))

(defn check-vertical
  [board]
  (first
   (filter #(every? :marked %)
           (vals (group-by #(mod (:i %) 5) board)))))

(defn apply-draw
  [coll draw]
  (map
   (fn [row]
     (if (= draw (:v row))
       (assoc row :marked true)
       row))
   coll))

(defn draw-ball
  [colls draw]
  (map
   (fn [coll]
     (let [updated (apply-draw coll draw)
           h-bingo (check-horizontal updated)
           v-bingo (check-vertical updated)]
       {:board updated :solution (or h-bingo v-bingo)}))
   colls))

(defn play-game
  [boards draws]
  (loop [boards boards
         solution nil
         last-draw nil
         draws draws]
    (cond (not (nil? solution)) (list solution last-draw)
          (empty? draws) nil
          :default (let [updated-results (draw-ball boards (first draws))
                         any-solution (first (filter
                                              #(not (nil? %))
                                              (map :solution updated-results)))
                         updated-boards (map :board updated-results)]
                     (recur updated-boards any-solution (first draws) (rest draws))))))
