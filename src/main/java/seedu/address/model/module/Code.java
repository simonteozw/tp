package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Code {
    public static final String MESSAGE_CONSTRAINTS =
        "Module code should start with 2 or 3 upper-case letters, follow by 4 numeric digits";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[A-Z]{2,3}[0-9]{4}$";

    public final String code;


    public Code(String code) {
        requireNonNull(code);
        checkArgument(isValidCode(code), MESSAGE_CONSTRAINTS);
        this.code = code;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Code // instanceof handles nulls
            && code.equals(((Code) other).code)); // state check
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
