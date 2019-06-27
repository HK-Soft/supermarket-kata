package kata.supermarket.core;

import java.util.Objects;

public class Unit {

    private final static String DEFAULT_UNIT_CODE = "unit";

    private String description;

    public Unit() {
        this.description = DEFAULT_UNIT_CODE;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return description.equalsIgnoreCase(unit.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
