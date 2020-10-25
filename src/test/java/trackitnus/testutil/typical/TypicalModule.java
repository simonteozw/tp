package trackitnus.testutil.typical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.module.Module;

public class TypicalModule {
    public static final Module CS1231S = new Module(new Code("CS1231S"), new Name("Discrete Structures"));
    public static final Module MA1101R = new Module(new Code("MA1101R"), new Name("Linear Algebra I"));
    public static final Module CS2030 = new Module(new Code("CS2030"), new Name("Programming Methodology II"));
    public static final Module CS3233 = new Module(new Code("CS3233"), new Name("Competitive Programming"));
    public static final Module CFG1002 = new Module(new Code("CFG1002"), new Name("Career Catalyst"));

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS1231S, MA1101R, CS2030, CS3233, CFG1002));
    }
}
