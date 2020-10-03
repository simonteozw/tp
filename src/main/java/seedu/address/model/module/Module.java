package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;

/**
 * Represents a Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {
    public static final String TYPE = "M";

    private final Code code;
    private final Title title;
    private final String gradeDist;
    private final List<Lesson> lessons;
    private final List<Task> tasks;

    /**
     * Every field must be present and not null.
     *
     * @param code
     * @param title
     * @param gradeDist
     * @param lessons
     * @param tasks
     */
    public Module(Code code, Title title, String gradeDist) {
        requireAllNonNull(code, title, gradeDist);
        this.code = code;
        this.title = title;
        this.gradeDist = gradeDist;
        this.lessons = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public static String getTYPE() {
        return TYPE;
    }

    public Code getCode() {
        return code;
    }

    public Title getTitle() {
        return title;
    }

    public String getGradeDist() {
        return gradeDist;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherLesson = (Module) other;
        return otherLesson.code.equals(code)
            && otherLesson.title.equals(title)
            && otherLesson.gradeDist.equals(gradeDist)
            && otherLesson.lessons.equals(lessons)
            && otherLesson.tasks.equals(tasks);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(code, title, gradeDist, lessons,tasks);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        // TODO: Implement this toString
        return "Module's toString hasn't been implemented";
    }

}
