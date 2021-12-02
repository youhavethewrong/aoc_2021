(ns aoc-2021.day1)

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
  (windowed-increases input 3))
