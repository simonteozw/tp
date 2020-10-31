package trackitnus.model.task;

import java.time.LocalDate;
import java.util.function.Predicate;

public class TaskOnDatePredicate implements Predicate<Task> {

    private final LocalDate date;

    public TaskOnDatePredicate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Task task) {
        return task.getDate().equals(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TaskOnDatePredicate // instanceof handles nulls
            && date.equals(((TaskOnDatePredicate) other).date)); // state check
    }
}
