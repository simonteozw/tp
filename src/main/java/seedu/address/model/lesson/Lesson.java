package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.model.commons.Address;
import seedu.address.model.commons.Code;

/**
 * Represents a Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {
    public static final String TYPE = "L";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");
    public static final String DATE_MESSAGE_CONSTRAINTS = "Date should be in the format d/MM/yyyy";
    public static final String WEIGHTAGE_MESSAGE_CONSTRAINTS =
        "Weightage should be in the form of a floating point number";
    public static final String TYPE_MESSAGE_CONSTRAINTS =
        "Type should be either 'lecture', 'tutorial', 'lab', 'recitation', or 'sectional'";
    public static final String CODE_MESSAGE_CONSTRAINTS = "Module code is invalid";

    private final Code code;
    private final Type type;
    private final LocalDate date;
    private final Address address;
    private final double weightage;

    /**
     * Every field must be present and not null.
     *
     * @param code
     * @param type
     * @param date
     * @param address
     * @param weightage
     */
    public Lesson(Code code, Type type, LocalDate date, Address address, double weightage) {
        requireAllNonNull(code, type, date, address, weightage);
        this.code = code;
        this.date = date;
        this.address = address;
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
            && otherLesson.date.equals(date)
            && otherLesson.address.equals(address)
            && (otherLesson.weightage == weightage);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(code, type, date, address, weightage);
    }

    @Override
    public String toString() {
        return getCode()
            + " "
            + getTypeStr()
            + " at: "
            + getDate()
            + " "
            + getAddress()
            + "with weightage of: "
            + getWeightage()
            + "%";
    }

    public Code getCode() {
        return code;
    }

    public Type getType() {
        return type;
    }

    public String getTypeStr() {
        return type.name();
    }

    public LocalDate getDate() {
        return date;
    }

    public Address getAddress() {
        return address;
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
