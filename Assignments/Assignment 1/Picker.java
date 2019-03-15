package warehouse.workers;

import java.util.Objects;

/**
 * A picker on the floor of the warehouse. The name uniquely identifies them.
 */
public class Picker {

    /**
     * The picker's name.
     */
    private final String name;

    /**
     * Create a picker named name.
     * @param name the picker name
     */
    public Picker(String name) {
        this.name = name;
    }

    /**
     * Return this pickers' name.
     * @return this pickers' name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Picker)) return false;
        Picker picker = (Picker) o;
        return Objects.equals(name, picker.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
