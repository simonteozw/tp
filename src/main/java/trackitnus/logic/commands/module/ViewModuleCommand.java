package trackitnus.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.Optional;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.Model;
import trackitnus.model.commons.Code;
import trackitnus.model.module.Module;

public class ViewModuleCommand extends Command {
    public static final String COMMAND_WORD = "info";

    public static final String MESSAGE_USAGE = Module.TYPE + COMMAND_WORD
        + ": View all the info of a module by its code.\n"
        + "Parameters: " + PREFIX_CODE + "CODE (must be an existing code)\n"
        + String.format("Example: %s %s %s/CS1231S", Module.TYPE, COMMAND_WORD, PREFIX_CODE);

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";
    private static final String MESSAGE_VIEW_MODULE_SUCCESS = "Here is the module you requested: %1$s";

    private final Code codeToView;

    public ViewModuleCommand(Code codeToView) {
        this.codeToView = codeToView;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Module> requestedModule = model.getModule(codeToView);


        if (requestedModule.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_MODULE_DOES_NOT_EXIST);
        }

        return new CommandResult(String.format(MESSAGE_VIEW_MODULE_SUCCESS, requestedModule.get()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ViewModuleCommand // instanceof handles nulls
            && codeToView.equals(((ViewModuleCommand) other).codeToView)); // state check
    }
}
