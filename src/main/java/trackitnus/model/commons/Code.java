package trackitnus.model.commons;

import static java.util.Objects.requireNonNull;

import trackitnus.commons.util.AppUtil;

/**
 * Represents a module code. Has form of AB1234 or ABC1234
 * Guarantees: immutable; is valid as declared in {@link #isValidCode(String)} (String)}
 */
public class Code {
    public static final String MESSAGE_CONSTRAINTS =
        "Module code should start with 2 or 3 upper-case letters, follow by 4 numeric digits and optionally a single "
            + "letter";

    public static final String VALIDATION_REGEX = "^[A-Z]{2,3}[0-9]{4}[A-Z]?$";

    public final String code;

    /**
     * Constructs a {@code Code}.
     *
     * @param code A valid code
     */
    public Code(String code) {
        requireNonNull(code);
        AppUtil.checkArgument(isValidCode(code), MESSAGE_CONSTRAINTS);
        this.code = code;
    }


    /**
     * @param test the string to test
     * @return true if the given string is a valid code.
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
