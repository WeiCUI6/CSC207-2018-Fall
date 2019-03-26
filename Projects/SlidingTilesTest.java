package fall2018.csc2017.GameCenter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import fall2018.csc2017.GameCenter.TileGame.SlidingTiles.SlidingTilesBoard;
import fall2018.csc2017.GameCenter.TileGame.SlidingTilesBoardManager;
import fall2018.csc2017.GameCenter.TileGame.Tile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for classes of SlidingTiles
 */
public class SlidingTilesTest {

    /**
     * A SlidingTiles Board manager for testing.
     */
    private SlidingTilesBoardManager slidingTilesBoardManager;

    /**
     * Make a set of tiles that are in order.
     *
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles(List<Integer> sequence) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = sequence.size();
        for (Integer i : sequence) {
            tiles.add(new Tile(i, numTiles));
        }
        return tiles;
    }

    /**
     * Mimic log in for the test
     * Make a solved SlidingTilesBoard.
     */
    private void setUpCorrect(ArrayList<Integer> sequence) {
        UserManager userManager = UserManager.getInstance();
        userManager.signUp("123456", "123456", new HashMap<String, User>());
        List<Tile> tiles = makeTiles(sequence);
        userManager.setCurrentGameType((int) Math.pow(sequence.size(), 0.5) - 3);
        SlidingTilesBoard board = new SlidingTilesBoard(tiles);
        slidingTilesBoardManager = new SlidingTilesBoardManager(board);
    }

    /**
     * Shuffle a few tiles.
     */
    private void swapFirstTwoTiles() {
        slidingTilesBoardManager.getSlidingTilesBoard().swapTiles(0, 0, 0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved SlidingTile Board unsolved.
     */
    @Test
    public void testIsSolved() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15));
        setUpCorrect(sequence);
        assertTrue(this.slidingTilesBoardManager.puzzleSolved());
        swapFirstTwoTiles();
        assertFalse(this.slidingTilesBoardManager.puzzleSolved());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15));
        setUpCorrect(sequence);
        SlidingTilesBoard board = this.slidingTilesBoardManager.getSlidingTilesBoard();
        assertEquals(1, board.getTile(0, 0).getId());
        assertEquals(2, board.getTile(0, 1).getId());
        board.swapTiles(0, 0, 0, 1);
        assertEquals(2, board.getTile(0, 0).getId());
        assertEquals(1, board.getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15));
        setUpCorrect(sequence);
        SlidingTilesBoard board = this.slidingTilesBoardManager.getSlidingTilesBoard();
        assertEquals(15, board.getTile(3, 2).getId());
        assertEquals(16, board.getTile(3, 3).getId());
        board.swapTiles(3, 3, 3, 2);
        assertEquals(16, board.getTile(3, 2).getId());
        assertEquals(15, board.getTile(3, 3).getId());
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15));
        setUpCorrect(sequence);
        assertTrue(this.slidingTilesBoardManager.isValidTap(11));
        assertTrue(this.slidingTilesBoardManager.isValidTap(14));
        assertFalse(this.slidingTilesBoardManager.isValidTap(10));
    }

    /**
     * Test whether touchMove works
     */
    @Test
    public void testUndo() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15));
        setUpCorrect(sequence);
        this.slidingTilesBoardManager.touchMove(14);
        this.slidingTilesBoardManager.undo();
        assertTrue(this.slidingTilesBoardManager.puzzleSolved());
    }

    /**
     * Test whether getBlankPosition works on 4x4 SlidingTiles Board
     */
    @Test
    public void testGetBlankPosition1() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 15, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 5));
        setUpCorrect(sequence);
        assertEquals(5, this.slidingTilesBoardManager.getBlankPosition());
    }

    /**
     * Test whether getBlankPosition works on 3x3 SlidingTiles Board
     */
    @Test
    public void testGetBlankPosition2() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 8, 3, 4, 5, 7, 6, 2));
        setUpCorrect(sequence);
        assertEquals(2, this.slidingTilesBoardManager.getBlankPosition());
    }

    /**
     * Test whether getBlankPosition works on 5x5 SlidingTiles Board
     */
    @Test
    public void testGetBlankPosition3() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 15, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 5, 16, 18, 17, 21, 19, 20, 24, 22, 23));
        setUpCorrect(sequence);
        assertEquals(22, this.slidingTilesBoardManager.getBlankPosition());
    }

    /**
     * Test whether getInversionNum works on 4x4 SlidingTiles Board
     */
    @Test
    public void testGetInversionNum1() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 15, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 5));
        setUpCorrect(sequence);
        assertEquals(9, this.slidingTilesBoardManager.getInversionNum());
    }

    /**
     * Test whether getInversionNum works on 3x3 SlidingTiles Board
     */
    @Test
    public void testGetInversionNum2() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 8, 3, 4, 5, 7, 6, 2));
        setUpCorrect(sequence);
        assertEquals(6, this.slidingTilesBoardManager.getInversionNum());
    }

    /**
     * Test whether getInversionNum works on 5x5 SlidingTiles Board
     */
    @Test
    public void testGetInversionNum3() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 15, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 5, 16, 18, 17, 21, 19, 20, 24, 22, 23));
        setUpCorrect(sequence);
        assertEquals(22, this.slidingTilesBoardManager.getInversionNum());
    }

    /**
     * Test whether solvable works on 4x4 SlidingTiles Board
     */
    @Test
    public void testSolvable1() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 15, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 5));
        setUpCorrect(sequence);
        assertFalse(this.slidingTilesBoardManager.solvable());
    }

    /**
     * Test whether solvable works on 3x3 SlidingTiles Board
     */
    @Test
    public void testSolvable2() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 8, 3, 4, 5, 7, 6, 2));
        setUpCorrect(sequence);
        assertTrue(this.slidingTilesBoardManager.solvable());
    }

    /**
     * Test whether solvable works on 5x5 SlidingTiles Board
     */
    @Test
    public void testSolvable3() {
        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 15, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 5, 16, 18, 17, 21, 19, 20, 24, 22, 23));
        setUpCorrect(sequence);
        assertTrue(this.slidingTilesBoardManager.solvable());
    }
}
