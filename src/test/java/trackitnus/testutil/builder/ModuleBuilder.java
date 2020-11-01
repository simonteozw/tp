package trackitnus.testutil.builder;

import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.module.Module;
import trackitnus.testutil.TestUtil;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_CODE = "CS3233";
    public static final String DEFAULT_NAME = "Competitive Programming";

    private Code code;
    private Name name;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        code = TestUtil.parseCode(DEFAULT_CODE);
        name = TestUtil.parseName(DEFAULT_NAME);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        code = moduleToCopy.getCode();
        name = moduleToCopy.getName();
    }

    /**
     * Sets the {@code Code} of the {@code Module} that we are building.
     */
    public ModuleBuilder withCode(String code) {
        this.code = TestUtil.parseCode(code);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.name = TestUtil.parseName(name);
        return this;
    }

    public Module build() {
        return new Module(code, name);
    }

}
