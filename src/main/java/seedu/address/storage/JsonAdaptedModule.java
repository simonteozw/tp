package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Address;
import seedu.address.model.commons.Code;
import seedu.address.model.commons.Name;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String code;
    private final String name;
    private final String desc;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("code") String code, @JsonProperty("name") String name,
                              @JsonProperty("desc") String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        code = source.getCode().code;
        name = source.getName().fullName;
        desc = source.getDesc();
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {

        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Code.class.getSimpleName()));
        }
        if (!Code.isValidCode(code)) {
            throw new IllegalValueException(Code.MESSAGE_CONSTRAINTS);
        }
        final Code modelCode = new Code(code);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (desc == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
        }
        final String modelDesc = desc;

        return new Module(modelCode, modelName, modelDesc);
    }

}
