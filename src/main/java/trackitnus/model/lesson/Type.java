package trackitnus.model.lesson;

public enum Type {
    LEC,
    TUT,
    LAB,
    REC,
    SEC;
    public static final String TYPE_MESSAGE_CONSTRAINTS =
        "Type should be either 'lec'/'lecture', 'tut'/'tutorial', 'lab'/'laboratory', " +
            "'rec'/'recitation', or 'sec'/'sectional'";
}

