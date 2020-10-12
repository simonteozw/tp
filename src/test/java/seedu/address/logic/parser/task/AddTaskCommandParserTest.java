package seedu.address.logic.parser.task;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.model.commons.Address;
import seedu.address.model.commons.Name;
import seedu.address.model.task.Task;

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
