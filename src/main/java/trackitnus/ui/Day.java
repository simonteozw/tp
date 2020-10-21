package trackitnus.ui;

import trackitnus.commons.util.CollectionUtil;

import java.util.Objects;

public class Day {
    private final String date;

    public Day(String date) {
        CollectionUtil.requireAllNonNull(date);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Day)) {
            return false;
        }

        Day otherDay = (Day) other;
        return otherDay.date.equals(date);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return " Desc: "
                + getDate();
    }

}
