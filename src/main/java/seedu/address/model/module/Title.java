package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


public class Title {

    public static final String MESSAGE_CONSTRAINTS =
        "Titles should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String title;

    public Title(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Title // instanceof handles nulls
            && title.equals(((Title) other).title)); // state check
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
