package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TrackIter;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableTrackIterTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTrackIterTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsTrackIter.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonTrackIter.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonTrackIter.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableTrackIter dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
            JsonSerializableTrackIter.class).get();
        TrackIter trackIterFromFile = dataFromFile.toModelType();
        TrackIter typicalPersonsTrackIter = TypicalPersons.getTypicalTrackIter();
        assertEquals(trackIterFromFile, typicalPersonsTrackIter);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackIter dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
            JsonSerializableTrackIter.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackIter dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
            JsonSerializableTrackIter.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTrackIter.MESSAGE_DUPLICATE_CONTACT,
            dataFromFile::toModelType);
    }

}
