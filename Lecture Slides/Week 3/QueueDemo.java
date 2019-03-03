public class QueueDemo {

    public static void fill(Queue<Integer> queue, int num) {
        for (int i = 0; i != num; i++) {
            queue.enqueue(i);
        }

//        queue.enqueue("Whoops");
    }

    public static void print(Queue<Integer> q) {
        // queue.size() times:
        //   dequeue, print, and enqueue
        for (int i = 0; i != q.size(); i++) {
            Integer o = q.dequeue();
            System.out.print(o + ", ");
            q.enqueue(o);
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListQueue<Integer>();
        fill(queue, 10);
        print(queue);
    }
}
