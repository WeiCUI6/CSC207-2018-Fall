/**
 * A person at the UofT with a name and a UTORid.
 */
public class Person {

    /**
     * Return this Person's UTORid.
     *
     * @return the Person's UTORid.
     */
    public String getId() {
        return id;
    }

    /**
     * Return this Person's name.
     *
     * @return this Person's name.
     */
    public String[] getName() {
        return name;
    }

    /**
     * The person's name, with the last name last.
     */
    private String[] name;

    /**
     * The person's UTORid.
     */
    private String id;

    /**
     * Create a new person with UTORid id and name n.
     *
     * @param id the UTORid
     * @param n  the name, with the last index containing the last name.
     */
    public Person(String id, String[] n) {
        this.id = id;
        this.name = n;
    }

}
