package trackitnus.model.lesson;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Objects;

import trackitnus.commons.util.CollectionUtil;
import trackitnus.model.commons.Code;

/**
 * Represents a Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {
    public static final ZoneId DEFAULT_TIME_ZONE = ZoneId.of("UTC+8");
    public static final String TYPE = "L";
    public static final String TIME_MESSAGE_CONSTRAINTS =
        "Starting time should be earlier than finishing time";
    public static final String LESSON_TIME_MESSAGE_CONSTRAINTS =
        "Lesson's time should be in the format \"ddd H:mm-H:mm\" (in 24-hour format), e.g. Mon 8:00-13:00";
    public static final String TYPE_MESSAGE_CONSTRAINTS =
        "Type should be either 'lecture', 'tutorial', 'lab', 'recitation', or 'sectional'";

    private final Code code;
    private final Type type;
    private final LessonDateTime time;

    /**
     * Every field must be present and not null.
     *
     * @param code
     * @param type
     * @param time
     */
    public Lesson(Code code, Type type, LessonDateTime time) {
        CollectionUtil.requireAllNonNull(code, type, time);
        this.code = code;
        this.time = time;
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
            && otherLesson.time.equals(time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type, time);
    }

    @Override
    public String toString() {
        return getCode()
            + " "
            + getTypeStr()
            + " at: "
            + getTime();
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

    public LessonDateTime getTime() {
        return time;
    }

    public DayOfWeek getWeekday() {
        return time.getWeekday();
    }

    public LocalTime getStartTime() {
        return time.getStartTime();
    }

    public LocalTime getEndTime() {
        return time.getEndTime();
    }

    /**
     * Returns true if the two lessons are the same
     * This methods is here for to act as a compatibility layer for UniqueModuleList
     */
    public boolean isSameLesson(Lesson lesson) {
        return this.equals(lesson);
    }
}
