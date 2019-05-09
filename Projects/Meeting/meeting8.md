# CSC207 2018Fall Project Phase2
##### Group 0667
---
### Meeting eight (24-11-2018)
##### What we have done:
1. The SudokuBoardManager class has completed with the function of undo operations, distinguish whether a tap is valid or not, change the tile tapped with the number user chose, distinguish whether the user has won the game.
2. The unit test for sliding tile puzzle game has finished.
3. The sudoku game has its own scoreboard for different users and the help image.
4. Know how to implement the game Hanoi.
##### What decisions we made:
1. We found that the function of board manager classes of sudoku and sliding tile are quite similar to each other. And these two games have the same GestureDetectGridView class. The StartingActivity class, MovementController class and HelpActivity class can also be designed to work for both sudoku and sliding tile depending on the game type. Therefore we may need to create a superclass for sudoku and sliding tile's board manager classes and fix all the places in starting activity, game activity, MovementController and HelpActivity classes that used them.
2. After understanding the principle of implementing Hanoi, we can now write it down to code.
3. complete the Hanoi and sudoku's scoreboard for each game and each user.
 
##### What each person should do next:
 - Zhuozi Zou and Xinyi Ji: create a super class for SudokuBoardManager class and SlidingTilesBoardManager and fix all the places in starting activity, game activity, MovementController, HelpActivity classes that used them depending on the game type.
 - Kewei Qiu and Wei CUI: Implement the Hanoi game by creating the function of choosing the difficulty of the game (ChooseLevelActivity class),drawing the game image of the game (Draw class), drawing the disk of Hanoi (DiskShape class), the connection between the screen and the controller(HanoiGameActivity, HanoiPerGameActivity class), the start program of this game(StartActivity class) and the movement of the disk in the screen (TextDrawable)
- Bohan Jiang: implement and connect the Hanoi and sudoku's scoreboard for each game and each user with the controller (HanoiScore, SudokuPerUserActivity, SudokuScoreBoardActivity classes).
 
