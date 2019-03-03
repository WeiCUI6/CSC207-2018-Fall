import java.util.Iterator;

/**
 * Manage people at the UofT.
 */
public class UofT {

    // There are no functions. Only methods.

    /**
     * The main method.
     *
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        Student s1 = new Student("mary00000000",
                new String[]{"Mary"}, 1);
        Student s2 = new Student("mary00000001",
                new String[]{"Mary"}, 2);
        Student s3 = new Student("frank",
                new String[]{"Frank"}, 3);

        ClassList classList = new ClassList();
        classList.add(s1);
        classList.add(s2);
        classList.add(s3);
        classList.drop(s1);
        for (Student s : classList) {
            System.out.println(s);
        }

        Iterator<Student> myIter = classList.iterator();
        myIter.next();

    }
}
