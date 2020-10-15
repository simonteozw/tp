package trackitnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import trackitnus.commons.exceptions.DataConversionException;
import trackitnus.model.ReadOnlyTrackIter;
import trackitnus.model.TrackIter;
import trackitnus.testutil.Assert;
import trackitnus.testutil.TypicalPersons;

public class JsonTrackIterStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTrackIterStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyTrackIter> readAddressBook(String filePath) throws Exception {
        return new JsonTrackIterStorage(Paths.get(filePath)).readTrackIter(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatTrackIter.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonTrackIter.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonTrackIter"
            + ".json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        TrackIter original = TypicalPersons.getTypicalTrackIter();
        JsonTrackIterStorage jsonTrackIterStorage = new JsonTrackIterStorage(filePath);

        // Save in new file and read back
        jsonTrackIterStorage.saveTrackIter(original, filePath);
        ReadOnlyTrackIter readBack = jsonTrackIterStorage.readTrackIter(filePath).get();
        assertEquals(original, new TrackIter(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addContact(TypicalPersons.HOON);
        original.removeContact(TypicalPersons.ALICE);
        jsonTrackIterStorage.saveTrackIter(original, filePath);
        readBack = jsonTrackIterStorage.readTrackIter(filePath).get();
        assertEquals(original, new TrackIter(readBack));

        // Save and read without specifying file path
        original.addContact(TypicalPersons.IDA);
        jsonTrackIterStorage.saveTrackIter(original); // file path not specified
        readBack = jsonTrackIterStorage.readTrackIter().get(); // file path not specified
        assertEquals(original, new TrackIter(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyTrackIter addressBook, String filePath) {
        try {
            new JsonTrackIterStorage(Paths.get(filePath))
                .saveTrackIter(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveAddressBook(new TrackIter(), null));
    }
}
