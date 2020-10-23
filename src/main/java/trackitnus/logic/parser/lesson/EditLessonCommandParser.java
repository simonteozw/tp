package trackitnus.logic.parser.lesson;

import static java.util.Objects.requireNonNull;
import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_DATE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.lesson.EditLessonCommand;
import trackitnus.logic.commands.lesson.EditLessonCommand.EditLessonDescriptor;
import trackitnus.logic.parser.ArgumentMultimap;
import trackitnus.logic.parser.ArgumentTokenizer;
import trackitnus.logic.parser.Parser;
import trackitnus.logic.parser.ParserUtil;
import trackitnus.logic.parser.Prefix;
import trackitnus.logic.parser.exceptions.ParseException;
import trackitnus.model.commons.Code;
import trackitnus.model.lesson.Type;

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
                PREFIX_CODE, PREFIX_TYPE, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CODE, PREFIX_TYPE)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                EditLessonCommand.MESSAGE_USAGE));
        }

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();

        Code code = ParserUtil.parseCode(argMultimap.getValue(PREFIX_CODE).get());
        editLessonDescriptor.setCode(code);

        Type type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        editLessonDescriptor.setType(type);

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editLessonDescriptor.setDate(ParserUtil.parseLessonDateTime(argMultimap.getValue(PREFIX_DATE).get()));
        }

        if (!editLessonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditLessonCommand.MESSAGE_NOT_EDITED);
        }

        return new EditLessonCommand(code, type, editLessonDescriptor);
    }
}
