# CSC207 2018Fall Project Phase2
##### Group 0667
---
### Meeting six (17-11-2018)
##### What we have done:
Since in phase two we need to implement 2 more new games. After searching online, we found a lot of different kinds of games such like 2048, match puzzle, sudoku, snake,24_point game and Hanoi... After discussing the difficulty and the design plan of each game, we finally decided to implement sudoku and Hanoi.
##### What decisions we made:
We first need to implement the solvable pattern of the game sliding tile to make board solvable all the time. We will implement the sudoku game at the same time by first, implementing how the sudoku game looks like during playing. We also need to generate the board before the user start the game and these are all quite different things from the game sliding tile.
 
##### What each person should do next:
 - Zhuozi Zou: Create the game interface, the buttons of the sudoku game (activity_sudoku_main.xml).
- Xinyi Ji: implement the game activity class for sudoku game(SudokuBoardManager) which will connect the buttons created in the game interface (activity_sudoku_main.xml) with the sudoku’s board manager. 
 - Wei CUI and Kewei Qiu: search online and implement the solvable pattern for sliding tile by adding a solvable method in SlidingTilesBoardManager class.
 - Bohan Jiang: implement the board class which will generate the sudoku board when the game start and change the tile tapped with the number the user chose.
 
