# CSC207 2018Fall Project Phase2
##### Group 0667
---
### Meeting seven(18-11-2018)
##### What we have done:
1. The board of the sliding tile puzzle app is now always solvable.
2. The game interface of sudoku game and the activity class connected to it is now implemented without adding the timing tool which will count the time of the user to play the whole game.
3. The board class of sudoku is now completed with the function to generate a new sudoku board or change the tile in the board with the number user chose.
##### What decisions we made:
1. implement a sudoku board manager class which will connect the game activity class of sudoku with the sudoku board class.
2. implement all the function of sudoku which is different from sliding tile such like the help function, the movement controller...
3. complete the unit test for sliding tile puzzle game.
 
##### What each person should do next:
 - Zhuozi Zou: add fields and undo method to SudokuBoardManager class
- Xinyi Ji: add isValidTap and touchMove methods to SudokuBoardManager class. Create the help image for sudoku (activity_sudoku_help.xml).
 - Kewei Qiu: add puzzleSolved method to SudokuBoardManager class. Work on the unit test for sliding tile puzzle game (SlidingTilesTest class).
 - Wei CUI: search online for the implementation of Hanoi game such as how to move the disks in the screen, how to draw the whole game image of Hanoi. Work on the unit test for sliding tile puzzle game (SlidingTilesTest class).
 - Bohan Jiang: add get and set methods to SudokuBoardManager class. Debug errors and improve the quality of SudokuBoard class. Create the scoreboard image of Sudoku game for each user (activity_sudoku_per_user.xml).
 
