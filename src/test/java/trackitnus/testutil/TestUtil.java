package trackitnus.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import trackitnus.commons.core.index.Index;
import trackitnus.logic.parser.ParserUtil;
import trackitnus.logic.parser.exceptions.ParseException;
import trackitnus.model.Model;
import trackitnus.model.commons.Address;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.contact.Contact;
import trackitnus.model.contact.Email;
import trackitnus.model.contact.Phone;
import trackitnus.model.lesson.LessonDateTime;
import trackitnus.model.lesson.Type;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle index of the contact in the {@code model}'s contact list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredContactList().size() / 2);
    }

    /**
     * Returns the last index of the contact in the {@code model}'s contact list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredContactList().size());
    }

    /**
     * Returns the contact in the {@code model}'s contact list at {@code index}.
     */
    public static Contact getContact(Model model, Index index) {
        return model.getFilteredContactList().get(index.getZeroBased());
    }


    public static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * this function act as an adapter to call ParserUtil, but omit checked the exception because it's expected that
     * any params passed in are valid
     */
    public static Index parseIndex(String oneBasedIndex) {
        try {
            return ParserUtil.parseIndex(oneBasedIndex);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this function act as an adapter to call ParserUtil, but omit checked the exception because it's expected that
     * any params passed in are valid
     */
    public static Name parseName(String name) {
        try {
            return ParserUtil.parseName(name);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this function act as an adapter to call ParserUtil, but omit checked the exception because it's expected that
     * any params passed in are valid
     */
    public static Phone parseOptionalPhone(Optional<String> phone) {
        try {
            return ParserUtil.parseOptionalPhone(phone);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this function act as an adapter to call ParserUtil, but omit checked the exception because it's expected that
     * any params passed in are valid
     */
    public static Address parseAddress(String address) {
        try {
            return ParserUtil.parseAddress(address);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this function act as an adapter to call ParserUtil, but omit checked the exception because it's expected that
     * any params passed in are valid
     */
    public static Email parseOptionalEmail(Optional<String> email) {
        try {
            return ParserUtil.parseOptionalEmail(email);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this function act as an adapter to call ParserUtil, but omit checked the exception because it's expected that
     * any params passed in are valid
     */
    public static Code parseCode(String code) {
        try {
            return ParserUtil.parseCode(code);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this function act as an adapter to call ParserUtil, but omit checked the exception because it's expected that
     * any params passed in are valid
     */
    public static Code parseOptionalCode(Optional<String> code) {
        try {
            return ParserUtil.parseOptionalCode(code);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this function act as an adapter to call ParserUtil, but omit checked the exception because it's expected that
     * any params passed in are valid
     */
    public static LocalDate parseDate(String date) {
        try {
            return ParserUtil.parseDate(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this function act as an adapter to call ParserUtil, but omit checked the exception because it's expected that
     * any params passed in are valid
     */
    public static String parseRemark(Optional<String> remark) {
        return ParserUtil.parseRemark(remark);
    }

    /**
     * this function act as an adapter to call ParserUtil, but omit checked the exception because it's expected that
     * any params passed in are valid
     */
    public static Type parseType(String type) {
        try {
            return ParserUtil.parseType(type);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this function act as an adapter to call ParserUtil, but omit checked the exception because it's expected that
     * any params passed in are valid
     */
    public static LessonDateTime parseLessonDateTime(String date) {
        try {
            return ParserUtil.parseLessonDateTime(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

}
