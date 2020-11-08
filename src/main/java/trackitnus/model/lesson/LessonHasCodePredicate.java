package trackitnus.model.lesson;

import java.util.function.Predicate;

import trackitnus.model.commons.Code;

/**
 * Tests that a {@code Lesson}'s {@code code} equals to a given {@code code}
 */
public class LessonHasCodePredicate implements Predicate<Lesson> {

    private final Code code;

    public LessonHasCodePredicate(Code code) {
        this.code = code;
    }

    @Override
    public boolean test(Lesson lesson) {
        return lesson.getCode().equals(code);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof LessonHasCodePredicate // instanceof handles nulls
            && code.equals(((LessonHasCodePredicate) other).code)); // state check
    }
}
