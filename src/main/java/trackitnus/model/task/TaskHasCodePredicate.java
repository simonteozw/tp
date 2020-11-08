package trackitnus.model.task;

import java.util.function.Predicate;

import trackitnus.model.commons.Code;

/**
 * Tests that a {@code Task}'s {@code code} equals to a given {@code code}
 */
public class TaskHasCodePredicate implements Predicate<Task> {

    private final Code code;

    public TaskHasCodePredicate(Code code) {
        this.code = code;
    }

    @Override
    public boolean test(Task task) {
        return task.belongsToModule(code);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TaskHasCodePredicate // instanceof handles nulls
            && code.equals(((TaskHasCodePredicate) other).code)); // state check
    }
}
