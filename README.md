# TicTacToe
TicTacToe_Game

========================
=== Overview ===
========================

This program implements two different parts: 
1. A Tic Tac Toe Game.
2. A Order and Chaos Game, which built based on the Tic Tac Toe Game.

To compile, simply run command: javac *.java
* You don't have to compile OnCGame.java and OrderAndChaos.jave if you only want to test Tic Tac Toe.

== Player ==
Class represents single player, has general properties such as name, but can be extended for more specific setting.

== Team ==
Class represents a general team, and has properties which all teams should have (team name, player in team, game statistic, etc.) 

== Board ==
Class represents a game board. Created in an abstract way so it can be extended for all kinds of board.

== Square Board ==
Subclass of board which represents a square game board used for tic tac toe and Orders and Chaos.

== Game ==
Class represents a board game in general. Created in an abstract way so it can be extended for different games.

== TicTacGame ==
Subclass of game which represent a tic tac toe game, specified for the tic tac toe game rules.

== OnCGame ==
Subclass of TicTacGame which represent Order and Chaos (alter version for tic tac toe), specified for the Order and Chaos game rules.

== TicTacToe ==
Main class to play the tic tac toe game, specified for for the game.

== OrderAndChaos ==
Main class to play the order and chaos game, specified for the game.


========================
=== Tic Tac Toe ===
========================

To start the game, run TicTacToe with command: java TicTacToe

== Setups ==

* The game have 2 modes: 
1. Classic mode of tic tac toe, which use a default game board of size 3x3 and players will be play as "O" and "X". Each player will play 1 turn in a round, and a player win if has 3 marks in a row/col/diagonal.
2. User defined mode, which allow players to:
	a. Provide a self-defined player name (will be used for game statistic).
	b. Use a self-defined mark instead of "O" and "X".
	c. Decide how many turns will each player take in a round.
	d. Scale size of the board (with in a bound).
	e. Number of marks required in a row/col/diagonal to win (with in a bound).

* Players may choose to switch playing sequence at beginning of each game.
* Players will place their mark on board by type the number of that slot.
* Players will see how many time they win and draw at end of each game.
* Players may choose to play another game with same setting or finish play when game end.


========================
=== Order and Chaos ===
========================

To start the game, run OrderAndChaos with command: java OrderAndChaos

== Setups ==

* The game allow players to:
	a. Provide a self-defined player name (will be used for game statistic).
	b. Decide how many turns will each player take in a round.

* Players may choose to switch role at beginning of each game.
* Players will place their mark on board by type the number of that slot.
* Players will see how many time they win at end of each game (since there's no draw condition for Order and Chaos).
* Players may choose to play another game with same setting or finish play when game end.
