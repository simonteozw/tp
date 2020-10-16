package trackitnus.logic.parser.module;

import org.junit.jupiter.api.Test;

import trackitnus.logic.commands.module.ViewModuleCommand;
import trackitnus.logic.parser.CommandParserTestUtil;
import trackitnus.model.commons.Code;

public class ViewModuleCommandParserTest {
    private final ViewModuleCommandParser parser = new ViewModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ViewModuleCommand expectedCommand = new ViewModuleCommand(new Code("CS1231S"));
        CommandParserTestUtil.assertParseSuccess(parser, " m/CS1231S", expectedCommand);
    }

}
