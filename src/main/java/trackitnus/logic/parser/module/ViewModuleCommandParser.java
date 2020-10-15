package trackitnus.logic.parser.module;

import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.stream.Stream;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.module.ViewModuleCommand;
import trackitnus.logic.parser.ArgumentMultimap;
import trackitnus.logic.parser.ArgumentTokenizer;
import trackitnus.logic.parser.Parser;
import trackitnus.logic.parser.ParserUtil;
import trackitnus.logic.parser.Prefix;
import trackitnus.logic.parser.exceptions.ParseException;
import trackitnus.model.commons.Code;

/**
 * Parses input arguments and creates a new ViewModuleCommand object
 */
public class ViewModuleCommandParser implements Parser<ViewModuleCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ViewModuleCommand
     * and returns an ViewModuleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_CODE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CODE)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ViewModuleCommand.MESSAGE_USAGE));
        }

        Code code = ParserUtil.parseCode(argMultimap.getValue(PREFIX_CODE).get());

        return new ViewModuleCommand(code);
    }

}
