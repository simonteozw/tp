package trackitnus.logic.parser.module;

import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_NAME;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.module.AddModuleCommand;
import trackitnus.logic.parser.ArgumentMultimap;
import trackitnus.logic.parser.ArgumentTokenizer;
import trackitnus.logic.parser.Parser;
import trackitnus.logic.parser.ParserUtil;
import trackitnus.logic.parser.exceptions.ParseException;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.module.Module;

/**
 * Parses input arguments and creates a new AddModuleCommand object
 */
public class AddModuleCommandParser implements Parser<AddModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddModuleCommand
     * and returns an AddModuleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_CODE, PREFIX_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CODE, PREFIX_NAME) || !argMultimap.getPreamble()
            .isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddModuleCommand.MESSAGE_USAGE));
        }

        Code code = ParserUtil.parseCode(argMultimap.getValue(PREFIX_CODE).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        Module module = new Module(code, name);

        return new AddModuleCommand(module);
    }

}
