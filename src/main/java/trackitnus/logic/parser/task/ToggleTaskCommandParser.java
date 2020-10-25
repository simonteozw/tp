package trackitnus.logic.parser.task;

import trackitnus.commons.core.Messages;
import trackitnus.commons.core.index.Index;
import trackitnus.logic.commands.task.ToggleTaskCommand;
import trackitnus.logic.parser.Parser;
import trackitnus.logic.parser.ParserUtil;
import trackitnus.logic.parser.exceptions.ParseException;

public class ToggleTaskCommandParser implements Parser<ToggleTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ToggleTaskCommand
     * and returns a ToggleTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ToggleTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ToggleTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ToggleTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
