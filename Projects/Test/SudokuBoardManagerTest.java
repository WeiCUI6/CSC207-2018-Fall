package fall2018.csc2017.GameCenter;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import fall2018.csc2017.GameCenter.TileGame.Sudoku.SudokuBoard;
import fall2018.csc2017.GameCenter.TileGame.Sudoku.SudokuValid;
import fall2018.csc2017.GameCenter.TileGame.SudokuBoardManager;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for SudokuBoardManager class
 */
public class SudokuBoardManagerTest {

    /**
     * The current user manager
     */
    private UserManager userManager = UserManager.getInstance();

    /**
     * The boardmanager for testing
     */
    private SudokuBoardManager boardManager;

    /**
     * The board saved for checking
     */
    private int[][] previousBoard = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 1, 4, 3, 6, 5, 8, 9, 7},
            {25, 6, 5, 8, 25, 7, 2, 1, 4},
            {8, 9, 7, 2, 1, 4, 3, 6, 5},
            {5, 3, 1, 6, 4, 2, 9, 7, 8},
            {6, 4, 2, 9, 7, 8, 5, 3, 1},
            {9, 7, 8, 5, 3, 1, 6, 4, 2}};

    /**
     * Mimic log in for the test
     * Create a Sudokuboard with solved board
     */
    @Before
    public void setUp() {
        userManager.signUp("123456", "123456", new HashMap<String, User>());
        int[][] newBoard = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 1, 4, 3, 6, 5, 8, 9, 7},
                {25, 6, 5, 8, 25, 7, 2, 1, 4},
                {8, 9, 7, 2, 1, 4, 3, 6, 5},
                {5, 3, 1, 6, 4, 2, 9, 7, 8},
                {6, 4, 2, 9, 7, 8, 5, 3, 1},
                {9, 7, 8, 5, 3, 1, 6, 4, 2}};
        SudokuBoard testBoard = new SudokuBoard(newBoard);
        this.boardManager = new SudokuBoardManager(testBoard);
    }

    /**
     * Test whether the method getSudokuBoard works properly
     */
    @Test
    public void getSudokuBoard() {
        setUp();

        int[][] testBoard = previousBoard;
        int[][] board = boardManager.getSudokuBoard().getSudokuBoard();
        assertArrayEquals(testBoard, board);

        board[4][4] = 9;
        board[4][0] = 3;
        testBoard[4][4] = 9;
        testBoard[4][0] = 3;
        assertArrayEquals(testBoard, board);
    }

    /**
     * Test whether the method setHasTileDisplay
     */
    @Test
    public void setHasTileDisplay() {
        setUp();

        boardManager.setHasTileDisplay();
        assertTrue(boardManager.getHasTileDisplay());
    }

    /**
     * Test whether the method setPositionDisplay works properly
     */
    @Test
    public void setPositionDisplay() {
        setUp();

        int[] testPosition = {1, 2, 3, 4};
        boardManager.setPositionDisplay(testPosition);
        assertArrayEquals(testPosition, boardManager.getPositionDisplay());
    }

    /**
     * Test whether the method getPostionDisplay works properly
     */
    @Test
    public void getPositionDisplay() {
        setUp();

        int[] testPosition = {1, 2, 3, 4};
        boardManager.setPositionDisplay(testPosition);

        assertArrayEquals(testPosition, boardManager.getPositionDisplay());
    }

    /**
     * Test whether the method getHasTileDisplay works properly
     */
    @Test
    public void getHasTileDisplay() {
        setUp();
        boardManager.setHasTileDisplay();
        assertTrue(boardManager.getHasTileDisplay());
    }

    /**
     * Test whether the undo method work properly
     */
    @Test
    public void undo() {
        // Set up the usermanager
        setUp();

        SudokuBoard board = boardManager.getSudokuBoard();
        // Try to undo at first, which results false
        boolean test1 = boardManager.undo();
        assertFalse(test1);

        // Save the coming change on the sudokuboard
        int[] arrayTest1 = {4, 4, 25};
        userManager.getCurrentUser().addSteps(6, arrayTest1);

        // Make the change on sudokuboard
        board.changeTiles(4, 4, 9);

        // Save the coming change on the sudokuboard
        int[] arrayTest2 = {4, 0, 25};
        userManager.getCurrentUser().addSteps(6, arrayTest2);

        // Make the change on the sudokuboard
        board.changeTiles(4, 0, 3);
        boolean test2 = boardManager.undo();

        // Judge whether undo returns true
        assertTrue(test2);
        int[][] boardTest1 = board.getSudokuBoard();

        // Judge whether undo works properly for the previous value
        assertEquals(25, boardTest1[4][0]);
        boolean test3 = boardManager.undo();

        // Judge whether undo returns true
        assertTrue(test3);
        int[][] boardTest2 = board.getSudokuBoard();
        assertEquals(25, boardTest2[4][4]);
    }

    /**
     * Test whether the touchMove works properly
     */
    @Test
    public void touchMove() {
        setUp();
        int[][] board = boardManager.getSudokuBoard().getSudokuBoard();
        int[] positionTest = {4, 4};
        boardManager.setPositionDisplay(positionTest);
        boardManager.touchMove(9);
        int[][] testArray = new int[9][9];
        for (int i = 0; i <= 8; i++) {
            testArray[i] = previousBoard[i].clone();
        }
        testArray[4][4] = 9;
        assertArrayEquals(testArray, board);

        positionTest[1] = 0;
        boardManager.setPositionDisplay(positionTest);
        boardManager.touchMove(3);
        testArray[4][0] = 3;
        assertArrayEquals(testArray, board);
    }

    /**
     * Test whether the isValidTap works properly
     */
    @Test
    public void isValidTap() {
        setUp();
        boardManager.setHasTileDisplay();
        int[] positionTest = {4, 4};
        boardManager.setPositionDisplay(positionTest);
        boolean testResult = boardManager.isValidTap(6);
        assertFalse(testResult);

        testResult = boardManager.isValidTap(9);
        assertTrue(testResult);

        positionTest[1] = 0;
        testResult = boardManager.isValidTap(3);
        assertTrue(testResult);

        testResult = boardManager.isValidTap(1);
        assertFalse(testResult);
    }

    /**
     * Test whether the method numAlreadyExisted works as expected
     */
    @Test
    public void numAlreadyExisted() {
        setUp();
        SudokuValid sudokuValid = boardManager.getSudokuValid();
        int[][] board = boardManager.getSudokuBoard().getSudokuBoard();
        int[] testRow = board[4];
        int[] testCol = board[4];
        int[] testSquare = sudokuValid.subSquare(4, board);

        assertTrue(boardManager.numAlreadyExisted(9, testRow, testCol, testSquare));
        assertFalse(boardManager.numAlreadyExisted(3, testRow, testCol, testSquare));
    }

    /**
     * Test whether the method puzzlesolved works properly
     */
    @Test
    public void puzzleSolved() {
        setUp();

        int[][] board = boardManager.getSudokuBoard().getSudokuBoard();
        assertFalse(boardManager.puzzleSolved());

        board[4][4] = 9;
        board[4][0] = 3;
        assertTrue(boardManager.puzzleSolved());
    }
}
