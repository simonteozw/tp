package trackitnus.logic.commands;

import trackitnus.commons.core.Messages;
import trackitnus.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(Messages.MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, "");
    }

}
