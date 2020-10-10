package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.model.commons.Address;
import seedu.address.model.commons.Code;

/**
 * Represents a Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {
    public static final String TYPE = "L";

    private final Code code;
    private final Type type;
    private final LocalDate time;
    private final Address location;
    private final double weightage;

    /**
     * Every field must be present and not null.
     *
     * @param code
     * @param type
     * @param time
     * @param location
     * @param weightage
     */
    public Lesson(Code code, Type type, LocalDate time, Address location, double weightage) {
        requireAllNonNull(code, type, time, location, weightage);
        this.code = code;
        this.time = time;
        this.location = location;
        this.weightage = weightage;
        this.type = type;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return otherLesson.code.equals(code)
            && otherLesson.type.equals(type)
            && otherLesson.time.equals(time)
            && otherLesson.location.equals(location)
            && (otherLesson.weightage == weightage);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(code, type, time, location, weightage);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Module: ")
                .append(getCode())
                .append(" Type: ")
                .append(getType())
                .append(" Time: ")
                .append(getTime())
                .append(" Location: ")
                .append(getLocation())
                .append(" Weightage: ")
                .append(getWeightage());
        return builder.toString();
    }

    public Code getCode() {
        return code;
    }

    public Type getType() {
        return type;
    }

    public LocalDate getTime() {
        return time;
    }

    public Address getLocation() {
        return location;
    }

    public double getWeightage() {
        return weightage;
    }

    /**
     * Returns true if the two lessons are the same
     * This methods is here for to act as a compatibility layer for UniqueModuleList
     */
    public boolean isSameLesson(Lesson lesson) {
        return this.equals(lesson);
    }
}
