package trackitnus.testutil.typical;

import java.util.Arrays;
import java.util.List;

import trackitnus.model.module.Module;
import trackitnus.model.util.SampleDataUtil;

public class TypicalModule {
    public static List<Module> get() {
        return Arrays.asList(SampleDataUtil.getSampleModules());
    }
}
