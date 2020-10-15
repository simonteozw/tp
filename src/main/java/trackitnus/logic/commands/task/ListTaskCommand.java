package trackitnus.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static trackitnus.model.Model.PREDICATE_SHOW_ALL_TASKS;

import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.model.Model;

public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
