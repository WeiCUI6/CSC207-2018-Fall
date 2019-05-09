# CSC207 2018Fall Project Phase2
##### Group 0667
---
### Meeting nine (25-11-2018)
##### What we have done:
1. we have created a super class for SudokuBoardManager class and SlidingTilesBoardManager. Fix all the places in starting activity, game activity, MovementController and HelpActivity classes that used them depending on the game type. That is the movement viewer and controller of these two games has been generated together and almost completed.
2. The game Hanoi has completed except for the saving function.
3. The scoreboard of Hanoi and sudoku for each game and each user has completed.
##### What decisions we made:
1. implement the save button and its function for Hanoi.
2. implement the timing tool which will be the basis of the score for game sudoku.
3. Since the User class is too long that it may be a code smell, we need to split it into 2 smaller classes.
4. Add some background pictures to the whole game to make it more comfortable for user to play.
5. implement the singleton design pattern for UserManager class to make the code clearer.
6. implement the unit test for both sudoku and Hanoi
7. solve all other code smells.
 
##### What each person should do next:
 - Zhuozi Zou: implement the singleton design pattern for UserManager class and fix the places in starting activity, game activity, board manger and all other classes that have used this. Fix the code smell with Bohan Jiang.
 - Xinyi Ji: Add background pictures to the game. Decompose the User class to User class and Score class and fix all the places that have used the original user class. Summarize the meeting documents and the readme file.
 - Kewei Qiu: Write the unit tests for SudokuBoardManager and UserManager class (SudokuBoardManagerTest, UserManagerTest)
- Wei CUI: Write the unit tests for SudokuBoard and SudokuValid class (SudokuBoardTest,SudokuPuzzleSolvedTest), implement and connect the save button and its function for Hanoi.
 - Bohan Jiang: Search online and implement the timing tool for sudoku game, fix the code smell with Zhuozi Zou.
 
