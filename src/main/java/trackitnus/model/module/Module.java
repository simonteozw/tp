package trackitnus.model.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javafx.scene.paint.Color;
import trackitnus.commons.util.CollectionUtil;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;

/**
 * Represents a Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {
    public static final String TYPE = "M";
    public static final ArrayList<Color> COLORS = new ArrayList<>(Arrays.asList(Color.web("#C24992"), Color.web(
        "#8F42DC"), Color.web("#4264DC"), Color.web("#42B4D8"), Color.web("#3CAC84"), Color.web("#6DAC3C"),
        Color.web("#DAEA29"), Color.web("#B79A02"), Color.web("#EE4E0A"), Color.web("#AD1A11")));

    private final Code code;
    private final Name name;

    /**
     * Every field must be present and not null.
     *
     * @param code
     * @param name
     */
    public Module(Code code, Name name) {
        CollectionUtil.requireAllNonNull(code, name);
        this.code = code;
        this.name = name;
    }

    public Code getCode() {
        return code;
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherLesson = (Module) other;
        return otherLesson.code.equals(code)
            && otherLesson.name.equals(name);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(code, name);
    }

    @Override
    public String toString() {
        return " Code: "
            + getCode()
            + " Name: "
            + getName();
    }

    /**
     * Returns true if both modules have the same code
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module module) {
        return this.code.equals(module.code);
    }
}
