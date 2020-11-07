package trackitnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackitnus.storage.JsonAdaptedLesson.MISSING_FIELD_MESSAGE_FORMAT;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import trackitnus.commons.exceptions.IllegalValueException;
import trackitnus.model.commons.Address;
import trackitnus.model.commons.Code;
import trackitnus.model.lesson.Lesson;
import trackitnus.testutil.Assert;
import trackitnus.testutil.typical.TypicalLessons;

public class JsonAdaptedLessonTest {
    private static final String INVALID_CODE = "cs1231s";
    private static final String INVALID_TIME = "Fri 20:00-18:00";
    private static final String INVALID_ADDRESS = "This address is invalid"
        + " because it is way way way too long";

    private static final Lesson VALID_LESSON = TypicalLessons.CS1101S_LEC;
    private static final String VALID_CODE = VALID_LESSON.getCode().code;
    private static final String VALID_TYPE = VALID_LESSON.getTypeStr();
    private static final String VALID_TIME = VALID_LESSON.getTime().toString();
    private static final String VALID_ADDRESS = VALID_LESSON.getAddress().value;

    @Test
    public void toModelType_validLessonDetails_returnsLesson() throws Exception {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_LESSON);
        assertEquals(VALID_LESSON, lesson.toModelType());
    }

    @Test
    public void toModelType_invalidCode_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
            new JsonAdaptedLesson(INVALID_CODE, VALID_TYPE, VALID_TIME, VALID_ADDRESS);
        String expectedMessage = Code.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullCode_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
            new JsonAdaptedLesson(null, VALID_TYPE, VALID_TIME, VALID_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Code.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
            new JsonAdaptedLesson(VALID_CODE, VALID_TYPE, INVALID_TIME, VALID_ADDRESS);
        String expectedMessage = Lesson.LESSON_TIME_MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
            new JsonAdaptedLesson(VALID_CODE, VALID_TYPE, null, VALID_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
            LocalDate.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
            new JsonAdaptedLesson(VALID_CODE, VALID_TYPE, VALID_TIME, INVALID_ADDRESS);
        String expectedMessage = Lesson.ADDRESS_MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
            new JsonAdaptedLesson(VALID_CODE, VALID_TYPE, VALID_TIME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
            Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }
}
