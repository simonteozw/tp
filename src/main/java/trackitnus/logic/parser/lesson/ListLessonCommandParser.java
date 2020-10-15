package trackitnus.logic.parser.lesson;

import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.Optional;
import java.util.stream.Stream;

import trackitnus.logic.commands.lesson.ListLessonCommand;
import trackitnus.logic.parser.ArgumentMultimap;
import trackitnus.logic.parser.ArgumentTokenizer;
import trackitnus.logic.parser.Parser;
import trackitnus.logic.parser.ParserUtil;
import trackitnus.logic.parser.Prefix;
import trackitnus.logic.parser.exceptions.ParseException;
import trackitnus.model.commons.Code;

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
