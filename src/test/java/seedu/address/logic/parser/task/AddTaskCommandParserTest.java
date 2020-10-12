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

    private Name name = new Name("Sample");
    private LocalDate date = LocalDate.parse("12/12/2020", Task.FORMATTER);
    private Address location = new Address("NUS");
    private double weightage = 25.5;
    private String remark = "Test";

    @Test
    public void parse_allFieldsPresent_success() {
        AddTaskCommand expectedCommand = new AddTaskCommand(new Task(name, date, location, weightage, remark));

        assertParseSuccess(parser, " n/Sample d/12/12/2020 a/NUS w/25.5 r/Test", expectedCommand);
    }
}
