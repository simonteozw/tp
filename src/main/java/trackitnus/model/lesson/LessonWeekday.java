package trackitnus.model.lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum LessonWeekday {
    Sun,
    Mon,
    Tue,
    Wed,
    Thu,
    Fri,
    Sat;
    public static LessonWeekday getLessonWeekDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        switch (dayOfWeek) {
        case SUNDAY:
            return Sun;
        case MONDAY:
            return Mon;
        case TUESDAY:
            return Tue;
        case WEDNESDAY:
            return Wed;
        case THURSDAY:
            return Thu;
        case FRIDAY:
            return Fri;
        case SATURDAY:
            return Sat;
        default:
            return null;
        }
    }
}
