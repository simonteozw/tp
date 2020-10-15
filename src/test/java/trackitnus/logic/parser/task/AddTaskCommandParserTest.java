package trackitnus.logic.parser.task;

import static trackitnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import trackitnus.logic.commands.task.AddTaskCommand;
import trackitnus.model.commons.Address;
import trackitnus.model.commons.Name;
import trackitnus.model.task.Task;

public class AddTaskCommandParserTest {
    private final AddTaskCommandParser parser = new AddTaskCommandParser();

    private final Name name = new Name("Sample");
    private final LocalDate date = LocalDate.parse("12/12/2020", Task.FORMATTER);
    private final Address address = new Address("NUS");
    private final double weightage = 25.5;
    private final String remark = "Test";

    @Test
    public void parse_allFieldsPresent_success() {
        AddTaskCommand expectedCommand = new AddTaskCommand(new Task(name, date, address, weightage, remark));

        assertParseSuccess(parser, " n/Sample d/12/12/2020 a/NUS w/25.5 r/Test", expectedCommand);
    }
}
