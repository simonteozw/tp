package trackitnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackitnus.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.commons.exceptions.IllegalValueException;
import trackitnus.logic.parser.ParserUtil;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.task.Task;
import trackitnus.testutil.Assert;
import trackitnus.testutil.typical.TypicalTask;

public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "Do Ass/gnment";
    private static final String INVALID_DATE = "02/01/20202";
    private static final String INVALID_CODE = "cs1101s";

    private static final Task VALID_TASK = TypicalTask.get(0);
    private static final String VALID_NAME = VALID_TASK.getName().value;
    private static final String VALID_DATE = VALID_TASK.getDate().format(ParserUtil.DATE_PATTERN);
    private static final String VALID_CODE = VALID_TASK.getCode().isPresent() ? VALID_TASK.getCode().get().code : null;
    private static final String VALID_REMARK = VALID_TASK.getRemark();

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASK);
        assertEquals(VALID_TASK, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task =
            new JsonAdaptedTask(INVALID_NAME, VALID_DATE, VALID_CODE, VALID_REMARK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DATE, VALID_CODE, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
            new JsonAdaptedTask(VALID_NAME, INVALID_DATE, VALID_CODE, VALID_REMARK);
        String expectedMessage = Messages.DATE_MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, null, VALID_CODE, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDate.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidCode_throwsIllegalValueException() {
        JsonAdaptedTask task =
            new JsonAdaptedTask(VALID_NAME, VALID_DATE, INVALID_CODE, VALID_REMARK);
        String expectedMessage = Code.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullCode_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DATE, null, VALID_REMARK);
        assertEquals(VALID_TASK, task.toModelType());
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DATE, VALID_CODE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Remark");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
