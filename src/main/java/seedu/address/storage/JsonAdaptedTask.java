package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Address;
import seedu.address.model.commons.Name;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final String date;
    private final String location;
    private final String weightage;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("date") String date,
                              @JsonProperty("location") String location, @JsonProperty("weightage") String weightage,
                              @JsonProperty("remark") String remark) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.weightage = weightage;
        this.remark = remark;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().fullName;
        date = source.getDate().format(Task.FORMATTER);
        location = source.getAddress().value;
        weightage = Double.toString(source.getWeightage());
        remark = source.getRemark();
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Task toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                LocalDate.class.getSimpleName()));
        }
        try {
            LocalDate.parse(date, Task.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(Task.DATE_MESSAGE_CONSTRAINTS);
        }
        final LocalDate modelDate = LocalDate.parse(date, Task.FORMATTER);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(location)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelLocation = new Address(location);

        if (weightage == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Weightage"));
        }
        try {
            Double.parseDouble(weightage);
        } catch (NumberFormatException e) {
            throw new IllegalValueException(Task.WEIGHTAGE_MESSAGE_CONSTRAINTS);
        }
        final double modelWeightage = Double.parseDouble(weightage);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Remark"));
        }
        final String modelRemark = remark;
        return new Task(modelName, modelDate, modelLocation, modelWeightage, modelRemark);
    }
}
