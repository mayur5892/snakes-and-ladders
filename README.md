# snakes-and-ladders

Snakes and Ladders console game utility

## pre-requites
Java

lein

## Usage

To start game for 15 turns

``` lein run 15```

TO start repl

```lein repl ```

snakes-and-ladders>lein repl
nREPL server started on port 63480 on host 127.0.0.1 - nrepl://127.0.0.1:63480
REPL-y 0.4.3, nREPL 0.6.0
Clojure 1.10.1
### adding snakes through Repl
snakes-and-ladders.core=> (add-snake 40 10)

{40 10}

snakes-and-ladders.core=> (add-snake 60 15)

{60 15}

### Fetching snakes configuration
snakes-and-ladders.core=> @snakes-position

{40 10, 60 15}

### Starting game inside repl
snakes-and-ladders.core=> (start-game 1)

Press 1 to play with Normal Dice

Press 2 to play with Crooked Dice

2

dice result for this turn  2

Player moved to position:  2  after  1 turns

nil



