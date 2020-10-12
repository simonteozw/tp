package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

public class ViewTaskCommand extends Command {

    public static final String COMMAND_WORD = "info";

    public static final String MESSAGE_USAGE = Task.TYPE + " " + COMMAND_WORD
        + ": Views the info of the task with the specified index.\n"
        + "Example: " + Task.TYPE + " " + COMMAND_WORD + " 1";
    private static final String MESSAGE_VIEW_TASK_SUCCESS = "Here is the task you requested: %1$s";

    private final Index targetIndex;

    public ViewTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToShow = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTask(taskToShow);
        return new CommandResult(String.format(MESSAGE_VIEW_TASK_SUCCESS, taskToShow));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ViewTaskCommand // instanceof handles nulls
            && targetIndex.equals(((ViewTaskCommand) other).targetIndex)); // state check
    }
}
