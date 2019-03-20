package fall2018.csc2017.slidingtiles;

import java.util.NoSuchElementException;
import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class Board extends Observable implements Serializable, Iterable<Tile> {

    /**
     * The number of rows.
     */
    final static int NUM_ROWS = 4;

    /**
     * The number of columns.
     */
    final static int NUM_COLS = 4;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles = new Tile[NUM_ROWS][NUM_COLS];

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles) {
        Iterator<Tile> itr = tiles.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                this.tiles[row][col] = itr.next();
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {

        return NUM_ROWS * NUM_COLS;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {

        Tile tempTile = this.tiles[row1][col1];
        this.tiles[row1][col1] = this.tiles[row2][col2];
        this.tiles[row2][col2] = tempTile;

        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    /**
     * Iterate over tiles on the board
     *
     * @return a new BoardIterator
     */
    @Override
    public Iterator<Tile> iterator() {

        return new BoardIterator();
    }

    /**
     * Class Board iterator
     */
    private class BoardIterator implements Iterator<Tile> {

        /**
         * The index of tiles on the board.
         */
        int nextIndex = 0;

        /**
         * Check whether there is a next tile on the board
         *
         * @return whether there is a next tile on the board
         */
        @Override
        public boolean hasNext() {
            return nextIndex != NUM_ROWS * NUM_COLS;
        }

        /**
         * Get the next tile on the board
         *
         * @return the next tile on the board
         */
        @Override
        public Tile next() {
            if (!hasNext()) {
                throw new NoSuchElementException(
                        "Run Out Of Tiles"
                );
            }

            int row = nextIndex / NUM_COLS;
            int col = nextIndex % NUM_COLS;
            Tile result = tiles[row][col];
            nextIndex++;
            return result;
        }
    }
}
