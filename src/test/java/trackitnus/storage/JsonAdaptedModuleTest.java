package trackitnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackitnus.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static trackitnus.testutil.typical.TypicalContacts.BENSON;

import org.junit.jupiter.api.Test;

import trackitnus.commons.exceptions.IllegalValueException;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.module.Module;
import trackitnus.testutil.Assert;
import trackitnus.testutil.typical.TypicalModule;

public class JsonAdaptedModuleTest {
    private static final String INVALID_NAME = "Inva/id Module";
    private static final String INVALID_CODE = "cs1231s";

    private static final Module VALID_MODULE = TypicalModule.get().get(0);
    private static final String VALID_NAME = VALID_MODULE.getName().value;
    private static final String VALID_CODE = VALID_MODULE.getCode().code;

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_MODULE);
        assertEquals(VALID_MODULE, module.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedModule module =
            new JsonAdaptedModule(VALID_CODE, INVALID_NAME);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedModule module =
            new JsonAdaptedModule(VALID_CODE,null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidCode_throwsIllegalValueException() {
        JsonAdaptedModule module =
            new JsonAdaptedModule(INVALID_CODE, VALID_NAME);
        String expectedMessage = Code.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullCode_throwsIllegalValueException() {
        JsonAdaptedModule module =
            new JsonAdaptedModule(null, VALID_NAME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Code.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }
}
