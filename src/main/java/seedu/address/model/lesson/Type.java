package seedu.address.model.lesson;

import seedu.address.logic.parser.exceptions.ParseException;

public enum Type {
    LEC,
    TUT,
    LAB,
    REC,
    SEC;
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

