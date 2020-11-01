package trackitnus.testutil.typical;

import java.util.Arrays;
import java.util.List;

import trackitnus.logic.parser.ParserUtil;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.task.Task;
import trackitnus.model.util.SampleDataUtil;

public class TypicalTask {

    public static List<Task> get() {
        return Arrays.asList(SampleDataUtil.getSampleTasks());
    }

    public static List<Task> getConst() { // the dates of tasks must be constant for JSON testing
        return Arrays.asList(
            new Task(new Name("Plan for Alex's birthday"), ParserUtil.parseValidDate("12/12/2020"),
                null, "Buy a cake"),
            new Task(new Name("Buy Mooncakes for Mum"), ParserUtil.parseValidDate("03/11/2020"), null,
                ""),
            new Task(new Name("Do Tutorial"), ParserUtil.parseValidDate("05/11/2020"),
                new Code("CS2100"), ""),
            new Task(new Name("Do this week's Mission"), ParserUtil.parseValidDate("03/11/2020"),
                new Code("CS1101S"), "Recursion"),
            new Task(new Name("Work on the final report"), ParserUtil.parseValidDate("02/11/2020"),
                new Code("GER1000H"), "min 500 words"),
            new Task(new Name("Study for Final Exam"), ParserUtil.parseValidDate("05/11/2020"),
                new Code("MA1101R"), "Focus on Diagonalisation"),
            new Task(new Name("Prepare for v1.3 Demo"), ParserUtil.parseValidDate("01/11/2020"),
                new Code("CS2103T"), "Ensure app runs smoothly"),
            new Task(new Name("Code Project 2"), ParserUtil.parseValidDate("20/11/2020"),
                new Code("CS2030S"),
                "(deadline is at the end of reading week)"));
    }

    public static Task get(int index) {
        return get().get(index);
    }
}
