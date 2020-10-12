package seedu.address.logic.parser.module;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.module.ViewModuleCommand;
import seedu.address.model.commons.Code;

public class ViewModuleCommandParserTest {
    private final ViewModuleCommandParser parser = new ViewModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ViewModuleCommand expectedCommand = new ViewModuleCommand(new Code("CS1231S"));
        assertParseSuccess(parser, " m/CS1231S", expectedCommand);
    }

}
