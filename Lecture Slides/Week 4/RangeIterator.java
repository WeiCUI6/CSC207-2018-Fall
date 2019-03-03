import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterate over a range of numbers.
 */
public class RangeIterator implements Iterator {

    /**
     * The start of the range.
     */
    private final int end;
    /**
     * The end of the range.
     */
    private final int start;
    /**
     * The next number.
     */
    private int next;

    /**
     * An iterator over the numbers in start .. end, inclusive.
     *
     * @param start the first number in the range
     * @param end   the last number in the range
     */
    public RangeIterator(int start, int end) {
        this.start = start;
        this.end = end;
        this.next = start;
    }

    @Override
    public boolean hasNext() {
        return next <= end;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException(
                    String.format("End of range [%s .. %s]", start, end));
        }

        // Pattern for method next:
        // 1. Remember the result
        // 2. Get ready for the next call to next (this is often in a helper method)
        // 3. Return what you remembered
        Integer result = next;
        next++;
        return result;
    }
}
