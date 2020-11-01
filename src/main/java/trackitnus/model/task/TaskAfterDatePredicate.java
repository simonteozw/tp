package trackitnus.model.task;

import java.time.LocalDate;
import java.util.function.Predicate;

public class TaskAfterDatePredicate implements Predicate<Task> {

    private final LocalDate date;

    public TaskAfterDatePredicate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Task task) {
        return task.getDate().isAfter(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TaskAfterDatePredicate // instanceof handles nulls
            && date.equals(((TaskAfterDatePredicate) other).date)); // state check
    }
}
