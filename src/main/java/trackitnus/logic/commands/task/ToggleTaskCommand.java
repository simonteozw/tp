package trackitnus.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static trackitnus.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import trackitnus.commons.core.Messages;
import trackitnus.commons.core.index.Index;
import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.Model;
import trackitnus.model.task.Task;

public class ToggleTaskCommand extends Command {

    public static final String COMMAND_WORD = "toggle";

    public static final String MESSAGE_USAGE = Task.TYPE + " " + COMMAND_WORD
        + ": Toggles completion status of the task identified by the index number used in the displayed task list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + Task.TYPE + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_TOGGLE_TASK_SUCCESS = "Toggled Task: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in TrackIt@NUS.";

    private final Index targetIndex;

    public ToggleTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToToggle = lastShownList.get(targetIndex.getZeroBased());
        Task toggledTask = taskToToggle.toggleDoneStatus();

        if (!taskToToggle.isSameTask(toggledTask) && model.hasTask(toggledTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToToggle, toggledTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(String.format(MESSAGE_TOGGLE_TASK_SUCCESS, toggledTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ToggleTaskCommand // instanceof handles nulls
            && targetIndex.equals(((ToggleTaskCommand) other).targetIndex)); // state check
    }
}
