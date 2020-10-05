package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TYPE;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.contact.AddContactCommand;
import seedu.address.logic.commands.contact.ClearContactCommand;
import seedu.address.logic.commands.contact.DeleteCommand;
import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.logic.commands.contact.FindCommand;
import seedu.address.logic.commands.contact.ListCommand;
import seedu.address.logic.parser.contact.AddCommandParser;
import seedu.address.logic.parser.contact.DeleteCommandParser;
import seedu.address.logic.parser.contact.EditCommandParser;
import seedu.address.logic.parser.contact.FindCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<type>[A-Z]\\s+)?"
        + "(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String type = matcher.group("type") == null ? "" : matcher.group("type").trim();
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (type) {
        case Contact.TYPE:
            switch (commandWord) {
            case AddContactCommand.COMMAND_WORD:
                return new AddCommandParser().parse(arguments);

            case EditCommand.COMMAND_WORD:
                return new EditCommandParser().parse(arguments);

            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommandParser().parse(arguments);

            case ClearContactCommand.COMMAND_WORD:
                return new ClearContactCommand();

            case FindCommand.COMMAND_WORD:
                return new FindCommandParser().parse(arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        case "":
            switch (commandWord) {
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        default:
            throw new ParseException(MESSAGE_INVALID_TYPE);
        }
    }

}
