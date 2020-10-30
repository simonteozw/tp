package trackitnus.testutil.typical;

import java.util.Arrays;
import java.util.List;

import trackitnus.model.task.Task;
import trackitnus.model.util.SampleDataUtil;

public class TypicalTask {
    public static List<Task> get() {
        return Arrays.asList(SampleDataUtil.getSampleTasks());
    }

    public static Task get(int index) {
        return get().get(index);
    }
}
