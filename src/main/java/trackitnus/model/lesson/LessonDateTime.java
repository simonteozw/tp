package trackitnus.model.lesson;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import trackitnus.commons.util.CollectionUtil;


public class LessonDateTime {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("H:mm");

    private final LessonWeekday weekday;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Every field must be present and not null.
     *
     * @param weekday
     * @param startTime
     * @param endTime
     */
    public LessonDateTime(LessonWeekday weekday, LocalTime startTime, LocalTime endTime) {
        CollectionUtil.requireAllNonNull(weekday, startTime, endTime);
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LessonWeekday getWeekday() {
        return this.weekday;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LessonDateTime)) {
            return false;
        }

        LessonDateTime otherDate = (LessonDateTime) other;
        return otherDate.weekday.equals(weekday)
                && otherDate.startTime.equals(startTime)
                && otherDate.endTime.equals(endTime);
    }

    @Override
    public String toString() {
        return weekday.name() + " "
                + startTime.format(FORMATTER) + "-"
                + endTime.format(FORMATTER);
    }
}
