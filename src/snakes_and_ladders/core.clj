(ns snakes-and-ladders.core)

(def players-position (atom {:player1 0}))

(defn- fetch-player-current-position [player-id]
  (@players-position player-id))

(defn move-player [current-position dice-throw]
  (let [next-position (+ current-position dice-throw)]
    (if (> next-position 100)
      current-position
      next-position)))

(defn dice-throw []
  4)

(defn play-next [player-id]
  (let [current-position (fetch-player-current-position player-id)
        next-position (move-player current-position (dice-throw))]))
