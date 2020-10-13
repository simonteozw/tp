package seedu.address.logic.parser.module;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.module.EditModuleCommand;

public class EditModuleCommandParserTest {
    private final EditModuleCommandParser parser = new EditModuleCommandParser();

    @Test
    public void parse_noFieldsProvided_failure() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_justModuleProvided_failure() {
        assertParseFailure(parser, "m/CS1231S", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditModuleCommand.MESSAGE_USAGE));
    }
}
