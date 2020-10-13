package seedu.address.logic.parser.lesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.lesson.EditLessonCommand;

public class EditLessonCommandParserTest {
    private final EditLessonCommandParser parser = new EditLessonCommandParser();

    @Test
    public void parse_noFieldsProvided_failure() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditLessonCommand.MESSAGE_USAGE));


    }

    @Test
    public void parse_justModuleProvided_failure() {
        assertParseFailure(parser, "m/CS1231S n/lecture", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditLessonCommand.MESSAGE_USAGE));
    }
}
