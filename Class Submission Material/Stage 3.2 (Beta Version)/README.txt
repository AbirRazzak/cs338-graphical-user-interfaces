README - Minesweeper Reborn (CS338 Final Project)
Abir Razzak
Amr439@drexel.edu

To run:
	Run the "Minesweeper.jar" file

Known Bugs:
	- Resetting the board causes the right-click flag functionality to be disabled.

Things that do not work upon submission of the project:
	- There are no bombs remaining counter
	- The reset button does not change to the death symbol when clicking on a bomb

Things that are currently working upon submission of the project:
	- The reset button successfully resets the board
	- Right-clicking a tile will flag it (asides from the bug mentioned previously)
	- Revealing empty tiles will reveal all adjacent number tiles, and revealing a number tile will reveal all adjacent empty tiles and any empty tiles adjacent to them (this chaining of empty tiles will also reveal adjacent number tiles to the empty ones)
	- The board is randomly generated, with 10 random bombs in each game instance
	- Clicking on a bomb will cause the game to no longer be playable until the reset button is clicked
