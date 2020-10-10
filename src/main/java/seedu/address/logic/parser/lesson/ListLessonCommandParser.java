package seedu.address.logic.parser.lesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.lesson.ListLessonCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Code;
import seedu.address.model.lesson.Type;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new ListLessonCommand object
 */
public class ListLessonCommandParser implements Parser<ListLessonCommand> {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ListLessonCommand
     * and returns a ListLessonCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListLessonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CODE);

        Optional<Code> codeOptional;
        if (argMultimap.getValue(PREFIX_CODE).isPresent()) {
            codeOptional = Optional.of(ParserUtil.parseCode(argMultimap.getValue(PREFIX_CODE).get()));
        }
        else {
            codeOptional = Optional.empty();
        }

        return new ListLessonCommand(codeOptional);
    }

}
