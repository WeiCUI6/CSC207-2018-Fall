package warehouse.workers;

import java.util.Objects;

/**
 * A sequencer on the floor of the warehouse.
 */
public class Sequencer {

    /**
     * The sequencer's name.
     */
    private final String name;

    /**
     * Create a sequencer named name.
     *
     * @param name the sequencer name
     */
    public Sequencer(String name) {
        this.name = name;
    }

    /**
     * Return this sequencer's name.
     *
     * @return this sequencer's name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sequencer)) return false;
        Sequencer sequencer = (Sequencer) o;
        return Objects.equals(name, sequencer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
