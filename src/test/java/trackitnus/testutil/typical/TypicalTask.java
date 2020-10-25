package trackitnus.testutil.typical;

import static trackitnus.testutil.typical.TypicalDates.DATE_ONE;
import static trackitnus.testutil.typical.TypicalDates.DATE_THREE;
import static trackitnus.testutil.typical.TypicalDates.DATE_TWO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.task.Task;

public class TypicalTask {

    public static final Task TASK_FULL = new Task(new Name("Doraemon"), DATE_ONE, new Code("CS1231S"),
        "Sample Remark");
    public static final Task TASK_FULL_DONE = new Task(new Name("Nobita Nobi"), DATE_TWO, new Code("CS2030S"),
        "Sample Remark", true);
    public static final Task TASK_NO_REMARK = new Task(new Name("Shizuka Minamoto"), DATE_THREE, new Code("CS2040"),
        "");
    public static final Task TASK_NO_CODE = new Task(new Name("Takeshi Goda"), DATE_THREE, null,
        "");

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK_FULL, TASK_FULL_DONE, TASK_NO_REMARK, TASK_NO_CODE));
    }
}
