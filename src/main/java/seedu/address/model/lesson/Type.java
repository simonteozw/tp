package seedu.address.model.lesson;

import seedu.address.logic.parser.exceptions.ParseException;

public enum Type {
    LEC,
    TUT,
    LAB,
    REC,
    SEC;

    /**
     * Parses the given {@code String} and returns a Type object.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public static Type parseType(String rawType) throws ParseException {
        switch (rawType) {
        case "lecture":
            return LEC;
        case "tutorial":
            return TUT;
        case "lab":
            return LAB;
        case "recitation":
            return REC;
        case "sectional":
            return SEC;
        default:
            throw new ParseException(Lesson.TYPE_MESSAGE_CONSTRAINTS);
        }
    }
}

