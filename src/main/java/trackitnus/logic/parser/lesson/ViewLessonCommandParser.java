package trackitnus.logic.parser.lesson;

import java.util.stream.Stream;

import trackitnus.commons.core.Messages;
import trackitnus.commons.core.index.Index;
import trackitnus.logic.commands.lesson.ViewLessonCommand;
import trackitnus.logic.parser.ArgumentMultimap;
import trackitnus.logic.parser.Parser;
import trackitnus.logic.parser.ParserUtil;
import trackitnus.logic.parser.Prefix;
import trackitnus.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewLessonCommand object
 */
public class ViewLessonCommandParser implements Parser<ViewLessonCommand> {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ViewLessonCommand
     * and returns a ViewLessonCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewLessonCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewLessonCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ViewLessonCommand.MESSAGE_USAGE), pe);
        }
    }

}
