package trackitnus.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import trackitnus.commons.util.CollectionUtil;
import trackitnus.model.commons.Address;
import trackitnus.model.commons.Name;


/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    public static final String TYPE = "T";

    public static final String DATE_MESSAGE_CONSTRAINTS = "Date should be in the format dd/MM/yyyy or dd/MM/yyyy hh:mm";
    public static final String WEIGHTAGE_MESSAGE_CONSTRAINTS = "Weightage should be in the"
        + " form of a floating point number";
    public static final String REMARK_MESSAGE_CONSTRAINTS = "Remarks can take any values, and it should not be blank";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Name name;
    private final LocalDate date;
    private final Address address;
    private final String remark;

    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param date
     * @param address
     * @param remark
     */
    public Task(Name name, LocalDate date, Address address, String remark) {
        CollectionUtil.requireAllNonNull(name, date, address, remark);
        this.name = name;
        this.date = date;
        this.address = address;
        this.remark = remark;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getRemark() {
        return remark;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.name.equals(name)
            && otherTask.date.equals(date)
            && otherTask.address.equals(address)
            && otherTask.remark.equals(remark);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, address, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append(" Date: ")
            .append(getDate())
            .append(" Address: ")
            .append(getAddress())
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
