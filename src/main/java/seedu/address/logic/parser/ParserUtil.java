package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Address;
import seedu.address.model.commons.Code;
import seedu.address.model.commons.Name;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Type;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        try {
            return LocalDate.parse(trimmedDate, Lesson.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(Lesson.DATE_MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String weightage} into a {@code double}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code weightage} is invalid.
     */
    public static double parseWeightage(String weightage) throws ParseException {
        requireNonNull(weightage);
        String trimmedWeightage = weightage.trim();
        try {
            return Double.parseDouble(trimmedWeightage);
        } catch (NumberFormatException e) {
            throw new ParseException(Lesson.WEIGHTAGE_MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String code} into a {@code Code}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code code} is invalid.
     */
    public static Code parseCode(String code) throws ParseException {
        requireNonNull(code);
        String trimmedCode = code.trim();
        try {
            return Code.parseCode(trimmedCode);
        } catch (ParseException e) {
            throw new ParseException(Lesson.CODE_MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String type} into a {@code Type}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code type} is invalid.
     */
    public static Type parseType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        try {
            return Type.parseType(trimmedType);
        } catch (ParseException e) {
            throw new ParseException(Lesson.TYPE_MESSAGE_CONSTRAINTS);
        }
    }
}
