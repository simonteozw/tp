package trackitnus.logic.parser.module;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static trackitnus.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.module.EditModuleCommand;

public class EditModuleCommandParserTest {
    private final EditModuleCommandParser parser = new EditModuleCommandParser();

    // note for Module: all user input needs leading space
    @Test
    public void parse_allFieldsPresent_success() {
        assertDoesNotThrow(() -> parser.parse(" m/CS1231S n/Sample"));
        assertDoesNotThrow(() -> parser.parse(" m/CS2030S n/Sample"));
    }

    @Test
    public void parse_noFieldsProvided_failure() {
        assertParseFailure(parser, "", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            EditModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_justModuleProvided_failure() {
        assertParseFailure(parser, " m/CS1231S", EditModuleCommand.MESSAGE_NOT_EDITED);
    }
}
