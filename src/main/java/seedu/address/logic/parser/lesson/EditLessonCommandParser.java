package seedu.address.logic.parser.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import java.util.stream.Stream;

import seedu.address.logic.commands.lesson.EditLessonCommand;
import seedu.address.logic.commands.lesson.EditLessonCommand.EditLessonDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Code;
import seedu.address.model.lesson.Type;

/**
 * Parses input arguments and creates a new EditLessonCommand object
 */
public class EditLessonCommandParser implements Parser<EditLessonCommand> {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditLessonCommand
     * and returns an EditLessonCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args,
                PREFIX_CODE, PREFIX_TYPE, PREFIX_DATE, PREFIX_ADDRESS, PREFIX_WEIGHTAGE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CODE, PREFIX_TYPE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLessonCommand.MESSAGE_USAGE));
        }

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();

        Code code = ParserUtil.parseCode(argMultimap.getValue(PREFIX_CODE).get());
        editLessonDescriptor.setCode(code);

        Type type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        editLessonDescriptor.setType(type);

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editLessonDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editLessonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_WEIGHTAGE).isPresent()) {
            editLessonDescriptor.setWeightage(ParserUtil.parseWeightage(argMultimap.getValue(PREFIX_WEIGHTAGE).get()));
        }

        if (!editLessonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditLessonCommand.MESSAGE_NOT_EDITED);
        }

        return new EditLessonCommand(code, type, editLessonDescriptor);
    }
}
