package trackitnus.logic.parser.module;

import static trackitnus.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.module.EditModuleCommand;

public class EditModuleCommandParserTest {
    private final EditModuleCommandParser parser = new EditModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
//        EditModuleCommand expected = new EditModuleCommand(new Code("CS1231S"),new EditModuleCommand
//        .EditModuleDescriptor())
//        assertParseSuccess(parser, "m/CS1231S n/Sample d/Sample", new Edi);
    }

    @Test
    public void parse_noFieldsProvided_failure() {
        assertParseFailure(parser, "", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            EditModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_justModuleProvided_failure() {
        assertParseFailure(parser, "m/CS1231S", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            EditModuleCommand.MESSAGE_USAGE));
    }
}
