package seedu.address.logic.parser.lesson;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.lesson.ListLessonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Code;

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
        } else {
            codeOptional = Optional.empty();
        }

        return new ListLessonCommand(codeOptional);
    }

}
