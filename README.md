# Cee-lo

This program simulates a game of Cee-lo; leveraging various Object-Oriented Programming concepts (such as Encapsulation and Inheritance) through the Model-View-Controller software design pattern.

## Demo

![](demo.gif)

## Game Features

Cee-lo is a gambling game where players roll three six-sided dice. In the context of this program, a player will play a modified version of Cee-lo against a computer for between 1-10 rounds. Each round, both the computer and the player will roll three six-sided dice. Rounds are dictated by 5 sets of outcomes, in ranking order:

1. A 4-5-6 is rolled (referred to as “c-lo”)
2. A triple is rolled (Ex: 5-5-5)
3. A point is rolled, where there’s 2 numbers that are the same, and one number is different (Ex: 5-5-2)
4. A 1-2-3 is rolled
5. 3 Distinct numbers are rolled, that are not 4-5-6 or 1-2-3 (ex. 2-3-4)

The player who wins the round will have a higher ranking outcome versus their opponent. For example, if the player gets 1-2-3, but the computer gets 2-3-4, the player would win the round, as Outcome 4 beats Outcome 5. If the player and the computer get the same outcome, the round is considered a draw.

Cee-lo is a gambling game, therefore there is a monetary element. Both player and the computer will both start with a $500 balance each. For each round, the player will be asked to place a bet and the computer will match their bet. The player’s bet must not exceed their balance and the computer’s balance. If the player wins the round, they receive double the amount of their bet, and the computer loses the entirety of their bet. For example, if the player bets $50 and wins the round, they will receive $100 (their original $50 + an extra $50), and the computer will lose $50. If the computer wins the round, the computer will receive double the amount of its bet, and the player will lose the entirety of their bet. The game will continue until one of the following three conditions are met:

- The player ends the game.
- The game reaches 10 rounds.
- Either the player or computer runs out of money.

After a game concludes, the results of each of the game's rounds and the game's final result are written to a text file.

## Running the Program

To run this program, [clone this repository](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository) and run/compile *Ceelo.java* in the *src* directory on an IDE that supports Java. This program is optimized to run on MacOS.
