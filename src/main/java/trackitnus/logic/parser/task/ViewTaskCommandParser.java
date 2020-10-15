package trackitnus.logic.parser.task;

import trackitnus.commons.core.Messages;
import trackitnus.commons.core.index.Index;
import trackitnus.logic.commands.task.ViewTaskCommand;
import trackitnus.logic.parser.Parser;
import trackitnus.logic.parser.ParserUtil;
import trackitnus.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewTaskCommand object
 */
public class ViewTaskCommandParser implements Parser<ViewTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindContactCommand
     * and returns a ViewTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ViewTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
