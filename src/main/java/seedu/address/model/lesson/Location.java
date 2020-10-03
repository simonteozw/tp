package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;

import seedu.address.model.contact.Name;

public class Location {
    public final String location;

    public Location(String location) {
        requireNonNull(location);
        this.location = location;
    }

    @Override
    public String toString() {
        return location;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Name // instanceof handles nulls
            && location.equals(((Location) other).location)); // state check
    }

    @Override
    public int hashCode() {
        return location.hashCode();
    }
}
