package trackitnus.logic.parser.task;

import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_DATE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_NAME;
import static trackitnus.logic.parser.CliSyntax.PREFIX_REMARK;

import java.time.LocalDate;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.task.AddTaskCommand;
import trackitnus.logic.parser.ArgumentMultimap;
import trackitnus.logic.parser.ArgumentTokenizer;
import trackitnus.logic.parser.Parser;
import trackitnus.logic.parser.ParserUtil;
import trackitnus.logic.parser.exceptions.ParseException;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.task.Task;

public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_CODE, PREFIX_REMARK);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddTaskCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        Code code = ParserUtil.parseOptionalCode(argMultimap.getValue(PREFIX_CODE));
        String remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK));

        Task task = new Task(name, date, code, remark);

        return new AddTaskCommand(task);
    }
}
