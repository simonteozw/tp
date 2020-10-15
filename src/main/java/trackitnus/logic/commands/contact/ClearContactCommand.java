package trackitnus.logic.commands.contact;

import static java.util.Objects.requireNonNull;

import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.model.Model;
import trackitnus.model.TrackIter;

/**
 * Clears the address book.
 */
public class ClearContactCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTrackIter(new TrackIter());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
