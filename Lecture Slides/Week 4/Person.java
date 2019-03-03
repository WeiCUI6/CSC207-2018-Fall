/** A person at the UofT with a <code>name</code> and a UTORid. */
public class Person {

    // Always make your instance variables private.

    /** The person's name, with the last name last. */
    private String[] name;

    /** This person's UTORid. */
    private String id;

    /**
     * A person at the UofT named n with UTORid id.
     * @param id the person's UTORid
     * @param n the person's name
     */
    public Person(String id, String[] n) {
        this.id = id;
        this.name = n;
    }

    // Provide appropriate accessors for any instance variables.

    /**
     * Return this person's UTORid.
     * @return this person's UTORid
     */
    public String getID() {
        return this.id;
    }

    /**
     * Return true if this person has the same UTORid as obj.
     * @param obj the other object to compare
     * @return whether this person has the same UTORid as obj
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Person &&
                this.id.equals(((Person) obj).id);
    }
}
