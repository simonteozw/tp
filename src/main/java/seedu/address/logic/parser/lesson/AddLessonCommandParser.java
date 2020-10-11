package seedu.address.logic.parser.lesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.lesson.AddLessonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Address;
import seedu.address.model.commons.Code;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Type;

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
                        PREFIX_CODE, PREFIX_TYPE, PREFIX_TIME, PREFIX_ADDRESS, PREFIX_WEIGHTAGE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CODE, PREFIX_TYPE, PREFIX_TIME, PREFIX_ADDRESS, PREFIX_WEIGHTAGE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLessonCommand.MESSAGE_USAGE));
        }

        Code code = ParserUtil.parseCode(argMultimap.getValue(PREFIX_CODE).get());
        Type type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        LocalDate time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        Address location = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        double weightage = ParserUtil.parseWeightage(argMultimap.getValue(PREFIX_WEIGHTAGE).get());

        Lesson lesson = new Lesson(code, type, time, location, weightage);

        return new AddLessonCommand(lesson);
    }

}
