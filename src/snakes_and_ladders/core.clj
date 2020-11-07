(ns snakes-and-ladders.core)

(def players-position (atom {:player1 0}))
(def snakes-position (atom {}))

(defn- fetch-player-current-position [player-id]
  (@players-position player-id))

(defn- update-player-position [id position]
  (swap! players-position assoc id position)
  position)

(defn move-player [id current-position dice-throw snakes-config]
  (let [next-position (+ current-position dice-throw)]
    (if (> next-position 100)
      current-position
      (update-player-position id (snakes-config next-position next-position)))))

(defn- save-snake [start end]
  (let [new-snake (hash-map start end)]
    (swap! snakes-position merge new-snake)
    new-snake))

(defn add-snake [start end]
  (if (> start end)
    (save-snake start end)
    {}))

(defn dice-throw []
  4)

(defn play-next [player-id]
  (let [current-position (fetch-player-current-position player-id)
        snakes-config (@snakes-position)
        next-position (move-player :player1 current-position (dice-throw) snakes-config)]))
