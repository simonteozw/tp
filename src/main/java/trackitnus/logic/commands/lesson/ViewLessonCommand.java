package trackitnus.logic.commands.lesson;

import static java.util.Objects.requireNonNull;

import java.util.List;

import trackitnus.commons.core.Messages;
import trackitnus.commons.core.index.Index;
import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.Model;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.task.Task;

public class ViewLessonCommand extends Command {
    public static final String COMMAND_WORD = "info";

    public static final String MESSAGE_USAGE = Lesson.TYPE + " " + COMMAND_WORD
        + ": Shows the details of a lesson. "
        + "Parameters: INDEX\n"
        + "Example: " + Lesson.TYPE + " " + COMMAND_WORD + " "
        + "1\n";

    private static final String MESSAGE_VIEW_LESSON_SUCCESS = "Here is the lesson you requested: %1$s";

    private final Index index;

    /**
     * Creates a ViewLessonCommand to view the specified {@code Lesson}
     *
     * @param index
     */
    public ViewLessonCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Task lessonToShow = lastShownList.get(index.getZeroBased());
        return new CommandResult(String.format(MESSAGE_VIEW_LESSON_SUCCESS, lessonToShow));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ViewLessonCommand // instanceof handles nulls
            && index.equals(((ViewLessonCommand) other).index)); // state check
    }
}
