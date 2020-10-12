package seedu.address.logic.parser.module;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.module.DeleteModuleCommand;
import seedu.address.model.commons.Code;

public class DeleteModuleCommandParserTest {
    private final DeleteModuleCommandParser parser = new DeleteModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        DeleteModuleCommand expectedCommand = new DeleteModuleCommand(new Code("CS1231S"));
        assertParseSuccess(parser, " m/CS1231S", expectedCommand);
    }

}
