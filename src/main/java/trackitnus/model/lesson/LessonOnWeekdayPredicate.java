package trackitnus.model.lesson;

import java.util.function.Predicate;

public class LessonOnWeekdayPredicate implements Predicate<Lesson> {

    private final DayOfWeek weekday;

    public LessonOnWeekdayPredicate(DayOfWeek weekday) {
        this.weekday = weekday;
    }

    @Override
    public boolean test(Lesson lesson) {
        return lesson.getWeekday().equals(weekday);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof LessonOnWeekdayPredicate // instanceof handles nulls
            && weekday.equals(((LessonOnWeekdayPredicate) other).weekday)); // state check
    }
}
