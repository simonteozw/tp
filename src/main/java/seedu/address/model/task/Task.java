package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.model.commons.Address;
import seedu.address.model.commons.Name;


/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    public static final String TYPE = "T";

    public static final String DATE_MESSAGE_CONSTRAINTS = "Date should be in the format d/MM/yyyy";
    public static final String WEIGHTAGE_MESSAGE_CONSTRAINTS = "Weightage should be in the"
        + " form of a floating point number";
    public static final String REMARK_MESSAGE_CONSTRAINTS = "Remarks can take any values, and it should not be blank";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");

    private final Name name;
    private final LocalDate date;
    private final Address location;
    private final double weightage;
    private final String remark;

    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param date
     * @param location
     * @param weightage
     * @param remark
     */
    public Task(Name name, LocalDate date, Address location, double weightage, String remark) {
        requireAllNonNull(name, date, location, weightage, remark);
        this.name = name;
        this.date = date;
        this.location = location;
        this.weightage = weightage;
        this.remark = remark;
    }

    public Name getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Address getLocation() {
        return location;
    }

    public double getWeightage() {
        return weightage;
    }

    public String getRemark() {
        return remark;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherLesson = (Task) other;
        return otherLesson.name.equals(name)
            && otherLesson.date.equals(date)
            && otherLesson.location.equals(location)
            && (otherLesson.weightage == weightage)
            && otherLesson.remark.equals(remark);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, location, weightage, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append(" Date: ")
            .append(getDate())
            .append(" Location: ")
            .append(getLocation())
            .append(" Weightage: ")
            .append(getWeightage())
            .append(" Remarks: ")
            .append(getRemark());
        return builder.toString();
    }

    /**
     * Returns true if the two tasks are the same
     * This methods is here for to act as a compatibility layer for UniqueTaskList
     */
    public boolean isSameTask(Task task) {
        return this.equals(task);
    }
}
