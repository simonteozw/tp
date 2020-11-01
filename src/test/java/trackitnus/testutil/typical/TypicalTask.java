package trackitnus.testutil.typical;

import java.time.LocalDate;
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
            new Task(new Name("Plan for Alex's birthday"), LocalDate.parse("12/12/2020", ParserUtil.DATE_PATTERN),
                null, "Buy a cake"),
            new Task(new Name("Buy Mooncakes for Mum"), LocalDate.parse("03/11/2020", ParserUtil.DATE_PATTERN), null,
                ""),
            new Task(new Name("Do Tutorial"), LocalDate.parse("05/11/2020", ParserUtil.DATE_PATTERN),
                new Code("CS2100"), ""),
            new Task(new Name("Do this week's Mission"), LocalDate.parse("03/11/2020", ParserUtil.DATE_PATTERN),
                new Code("CS1101S"), "Recursion"),
            new Task(new Name("Work on the final report"), LocalDate.parse("02/11/2020", ParserUtil.DATE_PATTERN),
                new Code("GER1000H"), "min 500 words"),
            new Task(new Name("Study for Final Exam"), LocalDate.parse("05/11/2020", ParserUtil.DATE_PATTERN),
                new Code("MA1101R"), "Focus on Diagonalisation"),
            new Task(new Name("Prepare for v1.3 Demo"), LocalDate.parse("01/11/2020", ParserUtil.DATE_PATTERN),
                new Code("CS2103T"), "Ensure app runs smoothly"),
            new Task(new Name("Code Project 2"), LocalDate.parse("20/11/2020", ParserUtil.DATE_PATTERN),
                new Code("CS2030S"),
                "(deadline is at the end of reading week)"));
    }

    public static Task get(int index) {
        return get().get(index);
    }
}
