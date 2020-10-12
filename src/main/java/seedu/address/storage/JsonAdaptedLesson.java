package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Address;
import seedu.address.model.commons.Code;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Type;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public class JsonAdaptedLesson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lesson's %s field is missing!";

    private final String code;
    private final String type;
    private final String date;
    private final String address;
    private final String weightage;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("code") String code, @JsonProperty("type") String type,
                             @JsonProperty("date") String date, @JsonProperty("address") String address,
                             @JsonProperty("weightage") String weightage) {
        this.code = code;
        this.type = type;
        this.date = date;
        this.address = address;
        this.weightage = weightage;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        code = source.getCode().code;
        type = source.getTypeStr();
        date = source.getDate().format(Task.FORMATTER);
        address = source.getAddress().value;
        weightage = Double.toString(source.getWeightage());
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Lesson toModelType() throws IllegalValueException {

        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Code.class.getSimpleName()));
        }
        if (!Code.isValidCode(code)) {
            throw new IllegalValueException(Code.MESSAGE_CONSTRAINTS);
        }
        final Code modelCode = new Code(code);

        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName()));
        }
        final Type modelType = Enum.valueOf(Type.class, type);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                LocalDate.class.getSimpleName()));
        }
        try {
            LocalDate.parse(date, Task.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(Task.DATE_MESSAGE_CONSTRAINTS);
        }
        final LocalDate modelTime = LocalDate.parse(date, Task.FORMATTER);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (weightage == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Weightage"));
        }
        try {
            Double.parseDouble(weightage);
        } catch (NumberFormatException e) {
            throw new IllegalValueException(Task.WEIGHTAGE_MESSAGE_CONSTRAINTS);
        }
        final double modelWeightage = Double.parseDouble(weightage);

        return new Lesson(modelCode, modelType, modelTime, modelAddress, modelWeightage);
    }
}
