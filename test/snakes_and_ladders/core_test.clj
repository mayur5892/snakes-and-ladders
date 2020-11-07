(ns snakes-and-ladders.core-test
  (:require [clojure.test :refer :all]
            [snakes-and-ladders.core :refer :all]))

(deftest move-player-test
  (testing "a player should move from initial position by the number on dice throw"
    (is (= 6 (move-player :player1 0 6 {}))))

  (testing "a player should move from current position by the number on dice throw"
    (is (= 52 (move-player :player2 50 2 {}))))

  (testing "a player should remain in current position if move exceed 100"
    (is (= 97 (move-player :player3 97 6 {}))))

  (testing "a player should move to snake end position if new position has snake"
    (is (= 16 (move-player :player3 50 6 {56 16})))))

(deftest add-snake-test
  (testing "add a snake starting from 97 to 13"
    (is (= {97 13} (add-snake 97 13))))

  (testing "adding a snake with start > end should return empty map"
    (is (= {} (add-snake 35 70)))))
