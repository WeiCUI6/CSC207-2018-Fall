package csc207.fall2018.slidingtiles;

import android.support.annotation.NonNull;

import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 * TODO: Make this implement Iterable<Tile>.
 */
public class Board extends Observable implements Serializable, Iterable<Tile> {

    /**
     * The number of rows.
     */
    final static int NUM_ROWS = 4;

    /**
     * The number of rows.
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
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     * @return the number of tiles on the board
     */
    int numTiles() {
        return Board.NUM_ROWS * Board.NUM_COLS;
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
        // TODO: swap
        Tile tmp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = tmp;
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
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new BoardIterator();
    }

    /**
     * Iterate through the board.
     */
    private class BoardIterator implements Iterator<Tile> {

        /**
         * (row, col) is the next Tile to return.
         */
        private int row;

        /**
         * (row, col) is the next Tile to return.
         */
        private int col;

        /**
         * An iterator for the current board.
         */
        BoardIterator() {
            this.row = 0;
            this.col = 0;
        }

        @Override
        public boolean hasNext() {
            return this.row != Board.NUM_ROWS;
        }

        @Override
        public Tile next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException("Reached end of board.");
            }

            Tile result = tiles[this.row][this.col];
            getReadyForNext();
            return result;
        }

        /**
         * Update col and row as appropriate.
         */
        private void getReadyForNext() {
            if (this.col == Board.NUM_COLS - 1) {
                this.row++;
                this.col = 0;
            } else {
                this.col++;
            }
        }
    }
}
