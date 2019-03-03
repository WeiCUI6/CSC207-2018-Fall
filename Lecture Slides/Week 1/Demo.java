
/**
 * Demonstrate printing, Strings, arrays, and instantiating an object.
 */
public class Demo {

    /**
     * The main method.
     *
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {

        // Expressions, printing, Strings, and arrays.
        System.out.println(3 + 4 * 2 - (-6 * 5));
        String myName = "Paul";
        String anotherVariable;
        String[] myStrings;
        myStrings = new String[]{"Paul", "Mike"};
        System.out.println(myStrings[1]);

        // A person.
        Person p = new Person("griespau", myStrings);
        System.out.println(p.getId());
        System.out.println(p.getName()[1]);
        String[] evilVillain = p.getName();
        evilVillain[1] = "Mario";
        System.out.println(p.getName()[1]); // Eww
        // Moral: never return a mutable instance variable.
    }
}
