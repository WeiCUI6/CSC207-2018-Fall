package fall2018.csc2017.GameCenter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import fall2018.csc2017.GameCenter.TileGame.Sudoku.SudokuBoard;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for class SudokuBoard
 */
public class SudokuBoardTest {

    /**
     * One example of a solved board
     */
    private final int[][] board = {{1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 1, 4, 3, 6, 5, 8, 9, 7},
            {3, 6, 5, 8, 9, 7, 2, 1, 4},
            {8, 9, 7, 2, 1, 4, 3, 6, 5},
            {5, 3, 1, 6, 4, 2, 9, 7, 8},
            {6, 4, 2, 9, 7, 8, 5, 3, 1},
            {9, 7, 8, 5, 3, 1, 6, 4, 2}};

    /**
     * A Sudokuboard with solved board
     */
    private SudokuBoard sudokuBoard;

    /**
     * Mimic log in for the test
     * Create a Sudokuboard with solved board
     */
    @Before
    public void setUp() {
        UserManager userManager = UserManager.getInstance();
        userManager.signUp("123456", "123456", new HashMap<String, User>());
        // Create a copy of sample board to avoid alias
        int[][] copyBoard = {{1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 1, 4, 3, 6, 5, 8, 9, 7},
                {3, 6, 5, 8, 9, 7, 2, 1, 4},
                {8, 9, 7, 2, 1, 4, 3, 6, 5},
                {5, 3, 1, 6, 4, 2, 9, 7, 8},
                {6, 4, 2, 9, 7, 8, 5, 3, 1},
                {9, 7, 8, 5, 3, 1, 6, 4, 2}};
        this.sudokuBoard = new SudokuBoard(copyBoard);
    }

    /**
     * Test whether getSudokuBoard gives the right board
     */
    @Test
    public void testGetSudokuBoard() {
        int[][] a = this.sudokuBoard.getSudokuBoard();
        assertArrayEquals(board, a);
    }

    /**
     * Test whether changeMethod1 works
     */
    @Test
    public void testChangeMethod1() {
        setUp();
        // get random generated 2 changed numbers
        int[] randomNumbers = this.sudokuBoard.changeMethod1();
        int num1 = randomNumbers[0];
        int num2 = randomNumbers[1];
        // get changed board
        int[][] changedBoard = this.sudokuBoard.getSudokuBoard();
        assertTrue(checkChangedNum(num1, num2, changedBoard));
        assertTrue(checkUnchangedNum(num1, num2, changedBoard));
    }

    /**
     * Test whether changeMethod2 works
     */
    @Test
    public void testChangeMethod2() {
        setUp();
        // get random generated 2 changed numbers
        int[] randomNumbers = this.sudokuBoard.changeMethod2();
        int num3 = randomNumbers[0];
        int num4 = randomNumbers[1];
        // get changed board
        int[][] changedBoard = this.sudokuBoard.getSudokuBoard();
        assertTrue(checkChangedCol(num3, num4, changedBoard));
        assertTrue(checkUnchangedCol(num3, num4, changedBoard));
    }

    /**
     * Test whether erase works
     */
    @Test
    public void testErase() {
        setUp();
        this.sudokuBoard.erase();
        // get erased board
        int[][] erasedBoard = this.sudokuBoard.getSudokuBoard();
        assertTrue(hasBlank(erasedBoard));
        assertTrue(rightNumOfBlanks(erasedBoard));
    }

    /**
     * Return whether there is at least one blank tile in erasedBoard
     * Helper method for testErase
     *
     * @param erasedBoard the erased sudoku board
     * @return true if there is at least one blank tile in erasedBoard
     */
    private boolean hasBlank(int[][] erasedBoard) {
        boolean result = false;
        for (int i1 = 0; i1 < 9; i1++) {
            for (int i2 = 0; i2 < 9; i2++) {
                if (erasedBoard[i1][i2] == 25) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Return whether there are 39 blank tiles in erasedBoard
     * Helper method for testErase
     *
     * @param erasedBoard the erased sudoku board
     * @return true if there are 39 blank tiles in erasedBoard
     */
    private boolean rightNumOfBlanks(int[][] erasedBoard) {
        int result = 0;
        for (int i1 = 0; i1 < 9; i1++) {
            for (int i2 = 0; i2 < 9; i2++) {
                if (erasedBoard[i1][i2] == 25) {
                    result++;
                }
            }
        }
        return result == 39;
    }

    /**
     * Check whether all positions of num1 and num2 swapped after changing
     * Helper method for testChangeMethod1
     *
     * @param num1         the first changed number
     * @param num2         the second changed number
     * @param changedBoard the changed sudoku board
     * @return true if all positions of num1 and num2 swapped after changing
     */
    private boolean checkChangedNum(int num1, int num2, int[][] changedBoard) {
        ArrayList<Integer> num1Original = getIndexOneNumber(num1, this.board);
        ArrayList<Integer> num2Original = getIndexOneNumber(num2, this.board);
        ArrayList<Integer> num1Changed = getIndexOneNumber(num1, changedBoard);
        ArrayList<Integer> num2Changed = getIndexOneNumber(num2, changedBoard);
        return (num1Original.equals(num2Changed) && num2Original.equals(num1Changed));
    }

    /**
     * Check whether other positions of numbers remain same after change positions of num1 and num2
     * Helper method for testChangeMethod1
     *
     * @param num1         the first changed number
     * @param num2         the second changed number
     * @param changedBoard the changed sudoku board
     * @return true if other positions of numbers remain same after change positions of num1 and num2
     */
    private boolean checkUnchangedNum(int num1, int num2, int[][] changedBoard) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (!(i == num1) & !(i == num2)) {
                nums.add(i);
            }
        }
        boolean result = true;
        for (int i : nums) {
            if (!(checkIndexMatch(i, changedBoard))) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Check whether num has the same positions in original board and changed board
     * Helper method for testChangeMethod1
     *
     * @param num          the given number
     * @param changedBoard the changed sudoku board
     * @return true if num has the same positions in original board and changed board
     */
    private boolean checkIndexMatch(int num, int[][] changedBoard) {
        ArrayList<Integer> numOriginal = getIndexOneNumber(num, this.board);
        ArrayList<Integer> numChanged = getIndexOneNumber(num, changedBoard);
        return numOriginal.equals(numChanged);
    }

    /**
     * Returns an ArrayList of positions of given number appears in board
     * Helper method for testChangeMethod1
     *
     * @param num   the given number
     * @param board the sudoku board
     * @return an ArrayList of indices of given number appears in board
     */
    private ArrayList<Integer> getIndexOneNumber(int num, int[][] board) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i1 = 0; i1 < 9; i1++) {
            for (int i2 = 0; i2 < 9; i2++) {
                if (board[i1][i2] == num) {
                    result.add(i2);
                }
            }
        }
        return result;
    }

    /**
     * Check check if changed columns remain same
     * Helper method for testChangeMethod2
     *
     * @param num3         index of the first changed column
     * @param num4         index of the second changed column
     * @param changedBoard the changed sudoku board
     * @return true if changed columns remain same
     */
    private boolean checkChangedCol(int num3, int num4, int[][] changedBoard) {
        // Get 2 original columns in position of num3 and num4
        int[] col1Original = column(num3, this.board);
        int[] col2Original = column(num4, this.board);
        // Get 2 changed columns in position of num3 and num4
        int[] col1Changed = column(num3, changedBoard);
        int[] col2Changed = column(num4, changedBoard);
        return (Arrays.equals(col1Original, col2Changed) && Arrays.equals(col1Changed, col2Original));
    }

    /**
     * Check check if unchanged columns remain same
     * Helper method for testChangeMethod2
     *
     * @param num3         index of the first changed column
     * @param num4         index of the second changed column
     * @param changedBoard the changed sudoku board
     * @return true if unchanged columns remain same
     */
    private boolean checkUnchangedCol(int num3, int num4, int[][] changedBoard) {
        boolean result = true;
        // get index of unchanged columns
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (!(i == num3) & !(i == num4)) {
                nums.add(i);
            }
        }
        // check if unchanged columns remain same one by one
        for (int i : nums) {
            int[] colOriginal = column(i, this.board);
            int[] colChanged = column(i, changedBoard);
            if (!(Arrays.equals(colOriginal, colChanged))) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Return an array of the i th column in sudoku board
     * Helper method for testChangeMethod2
     *
     * @param index index of subSquare
     * @param board the board
     * @return array of the i th column in sudoku board
     */
    private int[] column(int index, int[][] board) {
        int[] result = new int[9];
        int r;
        for (r = 0; r < board.length; r++) {
            result[r] = board[r][index];
        }
        return result;
    }
}
