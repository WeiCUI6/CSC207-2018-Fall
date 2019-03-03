import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Demonstrate the use of an iterator.
 */
public class DemoIterator {
    public static void main(String[] args) {
        rangeIntro();

        Iterator range = new RangeIterator(3, 6);
        int sum = sumIterator(range);
        System.out.println(sum);

        iterableIntro();
        foreachDemo();
    }

    private static void foreachDemo() {
        List<Integer> myList = new ArrayList<>();
        myList.add(0);
        myList.add(1);
        myList.add(2);

        // Why the independence is important: the foreach loop uses iterators under the hood.
        for (Integer i1 : myList) {
            for (Integer i2 : myList) {
                System.out.println(i1 + " " + i2);
            }
        }
    }

    private static void iterableIntro() {
        List<Integer> myList = new ArrayList<>();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        Iterator<Integer> iter1 = myList.iterator();
        Iterator<Integer> iter2 = myList.iterator();
        System.out.println("iter1: " + iter1.next());
        System.out.println("iter1: " + iter1.next());
        System.out.println("iter2: " + iter2.next());
        System.out.println("iter1: " + iter1.next());
        System.out.println("iter1: " + iter1.hasNext());
        System.out.println("iter2: " + iter2.hasNext());
        System.out.println("iter2: " + iter2.next());
    }

    private static void rangeIntro() {
        Iterator range = new RangeIterator(3, 6);
        System.out.println(range.hasNext());
        System.out.println(range.next());
        System.out.println(range.next());
        System.out.println(range.hasNext());
        System.out.println(range.next());
        System.out.println(range.next());
        System.out.println(range.hasNext());
//        System.out.println(range.next()); // This should throw an exception
    }

    private static int sumIterator(Iterator<Integer> range) {
        int sum = 0;
        while (range.hasNext()) {
            sum += (Integer) range.next();
        }
        return sum;
    }


}
