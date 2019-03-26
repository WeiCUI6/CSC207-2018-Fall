package fall2018.csc2017.GameCenter;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import fall2018.csc2017.GameCenter.TileGame.Sudoku.SudokuBoard;
import fall2018.csc2017.GameCenter.TileGame.SudokuBoardManager;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for method PuzzleSolved and its helper methods in class SudokuBoardManager
 */
public class SudokuPuzzleSolvedTest {

    /**
     * One example of a solved board
     */
    private final int[][] solvedBoard = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}};

    /**
     * One example of an unsolved board
     */
    private final int[][] unsolvedBoard = {{5, 3, 0, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {0, 5, 9, 7, 0, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 0, 4, 8, 5, 0},
            {9, 0, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 0, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}};

    /**
     * A sudoku board manager with an solved sudoku board
     */
    private SudokuBoardManager sManager;

    /**
     * A sudoku board manager with an unsolved sudoku board
     */
    private SudokuBoardManager uManager;

    /**
     * Mimic log in for the test
     * Create 2 board managers with solved and unsolved boards respectively
     */
    @Before
    public void setUpCorrect() {
        UserManager userManager = UserManager.getInstance();
        userManager.signUp("123456", "123456", new HashMap<String, User>());
        SudokuBoard sSudokuBoard = new SudokuBoard(solvedBoard);
        SudokuBoard uSudokuBoard = new SudokuBoard(unsolvedBoard);
        this.sManager = new SudokuBoardManager(sSudokuBoard);
        this.uManager = new SudokuBoardManager(uSudokuBoard);
    }


    /**
     * Test whether puzzleSolved works on solved board
     */
    @Test
    public void testPuzzleSolvedOnRightBoard() {
        setUpCorrect();
        assertTrue(sManager.puzzleSolved());
    }


    /**
     * Test whether puzzleSolved works on unsolved board
     */
    @Test
    public void testPuzzleSolvedOnWrongBoard() {
        setUpCorrect();
        assertFalse(uManager.puzzleSolved());
    }


    /**
     * Test whether allRowsValid works
     */
    @Test
    public void testAllRowsValid() {
        setUpCorrect();
        assertTrue(sManager.getSudokuValid().allRowsValid(solvedBoard));
    }


    /**
     * Test whether listValid works
     */
    @Test
    public void testListValid() {
        setUpCorrect();
        int[] l = {5, 3, 4, 6, 7, 8, 9, 1, 2};
        assertTrue(sManager.getSudokuValid().listValid(l));
    }


    /**
     * Test whether allColumnsValid works on solved board
     */
    @Test
    public void testAllColumnsValid1() {
        setUpCorrect();
        assertTrue(sManager.getSudokuValid().allColumnsValid(solvedBoard));
    }


    /**
     * Test whether allColumnsValid works on unsolved board
     */
    @Test
    public void testAllColumnsValid2() {
        setUpCorrect();
        assertFalse(uManager.getSudokuValid().allColumnsValid(unsolvedBoard));
    }


    /**
     * Test whether columns works
     */
    @Test
    public void testColumns() {
        setUpCorrect();
        int[][] r = sManager.getSudokuValid().columns(solvedBoard);
        int[][] e = {{5, 6, 1, 8, 4, 7, 9, 2, 3},
                {3, 7, 9, 5, 2, 1, 6, 8, 4},
                {4, 2, 8, 9, 6, 3, 1, 7, 5},
                {6, 1, 3, 7, 8, 9, 5, 4, 2},
                {7, 9, 4, 6, 5, 2, 3, 1, 8},
                {8, 5, 2, 1, 3, 4, 7, 9, 6},
                {9, 3, 5, 4, 7, 8, 2, 6, 1},
                {1, 4, 6, 2, 9, 5, 8, 3, 7},
                {2, 8, 7, 3, 1, 6, 4, 5, 9}};
        assertArrayEquals(e, r);
    }


    /**
     * Test whether column works
     */
    @Test
    public void testColumn() {
        setUpCorrect();
        int[] r = sManager.getSudokuValid().column(0, solvedBoard);
        int[] e = {5, 6, 1, 8, 4, 7, 9, 2, 3};
        assertArrayEquals(e, r);
    }


    /**
     * Test whether allSubsquaresValid works on solved board
     */
    @Test
    public void testAllSubsquaresValid1() {
        setUpCorrect();
        assertTrue(sManager.getSudokuValid().allSubsquaresValid(solvedBoard));
    }


    /**
     * Test whether allSubsquaresValid works on unsolved board
     */
    @Test
    public void testAllSubsquaresValid2() {
        setUpCorrect();
        assertFalse(uManager.getSudokuValid().allSubsquaresValid(unsolvedBoard));
    }


    /**
     * Test whether subSquares works
     */
    @Test
    public void testSubsquares() {
        setUpCorrect();
        int[][] r = sManager.getSudokuValid().subSquares(solvedBoard);
        int[][] e = {{5, 3, 4, 6, 7, 2, 1, 9, 8},
                {6, 7, 8, 1, 9, 5, 3, 4, 2},
                {9, 1, 2, 3, 4, 8, 5, 6, 7},
                {8, 5, 9, 4, 2, 6, 7, 1, 3},
                {7, 6, 1, 8, 5, 3, 9, 2, 4},
                {4, 2, 3, 7, 9, 1, 8, 5, 6},
                {9, 6, 1, 2, 8, 7, 3, 4, 5},
                {5, 3, 7, 4, 1, 9, 2, 8, 6},
                {2, 8, 4, 6, 3, 5, 1, 7, 9}};
        assertArrayEquals(e, r);
    }


    /**
     * Test whether subSquare works
     */
    @Test
    public void testSubsquare() {
        setUpCorrect();
        int[] r = sManager.getSudokuValid().subSquare(1, solvedBoard);
        int[] s = {6, 7, 8, 1, 9, 5, 3, 4, 2};
        assertArrayEquals(s, r);
    }
}
