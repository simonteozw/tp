package trackitnus.testutil.builder;

import trackitnus.model.commons.Code;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.lesson.LessonDateTime;
import trackitnus.model.lesson.Type;
import trackitnus.testutil.TestUtil;

/**
 * A utility class to help with building Lesson objects.
 */
public class LessonBuilder {

    public static final String DEFAULT_CODE = "CS2103T";
    public static final String DEFAULT_TYPE = "lec";
    public static final String DEFAULT_TIME = "Mon 10:00-12:00";

    private Code code;
    private Type type;
    private LessonDateTime time;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        code = TestUtil.parseCode(DEFAULT_CODE);
        type = TestUtil.parseType(DEFAULT_TYPE);
        time = TestUtil.parseLessonDateTime(DEFAULT_TIME);
    }

    /**
     * Initializes the LessonBuilder with the data of {@code lessonToCopy}.
     */
    public LessonBuilder(Lesson lessonToCopy) {
        code = lessonToCopy.getCode();
        type = lessonToCopy.getType();
        time = lessonToCopy.getTime();
    }

    /**
     * Sets the {@code Code} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withCode(String code) {
        this.code = TestUtil.parseCode(code);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withType(String type) {
        this.type = TestUtil.parseType(type);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withTime(String time) {
        this.time = TestUtil.parseLessonDateTime(time);
        return this;
    }

    public Lesson build() {
        return new Lesson(code, type, time);
    }

}
