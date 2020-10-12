package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class NameContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof seedu.address.model.task.NameContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((seedu.address.model.task.NameContainsKeywordsPredicate) other).keywords)); // state
        // check
    }

}
