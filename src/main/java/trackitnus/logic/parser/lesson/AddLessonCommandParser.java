package trackitnus.logic.parser.lesson;

import static trackitnus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_DATE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.lesson.AddLessonCommand;
import trackitnus.logic.parser.ArgumentMultimap;
import trackitnus.logic.parser.ArgumentTokenizer;
import trackitnus.logic.parser.Parser;
import trackitnus.logic.parser.ParserUtil;
import trackitnus.logic.parser.Prefix;
import trackitnus.logic.parser.exceptions.ParseException;
import trackitnus.model.commons.Address;
import trackitnus.model.commons.Code;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.lesson.LessonDateTime;
import trackitnus.model.lesson.Type;

/**
 * Parses input arguments and creates a new AddLessonCommand object
 */
public class AddLessonCommandParser implements Parser<AddLessonCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddLessonCommand
     * and returns an AddLessonCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLessonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args,
                PREFIX_CODE, PREFIX_TYPE, PREFIX_DATE, PREFIX_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_CODE, PREFIX_TYPE, PREFIX_DATE, PREFIX_ADDRESS) || !argMultimap
            .getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddLessonCommand.MESSAGE_USAGE));
        }

        Code code = ParserUtil.parseCode(argMultimap.getValue(PREFIX_CODE).get());
        Type type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        LessonDateTime date = ParserUtil.parseLessonDateTime(argMultimap.getValue(PREFIX_DATE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());

        Lesson lesson = new Lesson(code, type, date, address);

        return new AddLessonCommand(lesson);
    }

}
