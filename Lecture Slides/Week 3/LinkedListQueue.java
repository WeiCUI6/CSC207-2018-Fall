import java.util.LinkedList;

public class LinkedListQueue<T> implements Queue<T> {

    private LinkedList contents = new LinkedList();

    @Override
    public void enqueue(T o) {
        this.contents.addLast(o);
    }

    @Override
    public T dequeue() {
        return (T) this.contents.removeFirst();
    }

    @Override
    public T front() {
        return (T) this.contents.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return this.contents.isEmpty();
    }

    @Override
    public int size() {
        return this.contents.size();
    }
}
