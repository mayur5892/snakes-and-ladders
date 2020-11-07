(ns snakes-and-ladders.core)

(def players-position (atom {:player1 0}))
(def snakes-position (atom {}))

(defn- fetch-player-current-position [player-id]
  (@players-position player-id))

(defn- update-player-position [id position]
  (swap! players-position assoc id position)
  position)

(defn- save-snake [start end]
  (let [new-snake (hash-map start end)]
    (swap! snakes-position merge new-snake)
    new-snake))

(defn move-player [id current-position dice-throw snakes-config]
  (let [next-position (+ current-position dice-throw)]
    (if (> next-position 100)
      current-position
      (update-player-position id (snakes-config next-position next-position)))))

(defn add-snake [start end]
  (if (> start end)
    (save-snake start end)
    {}))

(defn- throw-crooked-dice []
  (first (shuffle [2 4 6])))

(defn- throw-normal-dice []
  (first (shuffle [1 2 3 4 5 6])))

(defn- throw-dice [dice-mode]
  (if (= dice-mode 1)
    (throw-normal-dice)
    (throw-crooked-dice)))

(defn- play-next-turn [player-id dice-result]
  (println "dice result for this turn " dice-result)
  (let [current-position (fetch-player-current-position player-id)
        snakes-config @snakes-position]
    (move-player player-id current-position dice-result snakes-config)))

(defn- prompt [message]
  (println message)
  (read-line))

(defn- select-dice []
  (try
    (let [user-input (Integer/parseInt (prompt "Press 1 to play with Normal Dice \nPress 2 to play with Crooked Dice"))]
      (if (#{1 2} user-input)
        user-input
        (do
          (println "Invalid Input. Press 1 or 2")
          (select-dice))
        ))
    (catch Exception _
      (println "Characters are not allowed. Press 1 or 2")
      (select-dice))))

(defn- start-game [number-of-plays]
  "Starting Snakes and Ladders Game for 1 Player"
  (let [dice-mode (select-dice)]
    (dotimes [_ number-of-plays]
      (play-next-turn :player1 (throw-dice dice-mode)))
    (println "Player moved to position: " (@players-position :player1) " after " number-of-plays "turns")))

(defn -main [number-of-plays]
  (start-game (Integer/parseInt number-of-plays)))