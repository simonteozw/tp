package seedu.address.logic.commands.lesson;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.Code;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Type;

public class DeleteLessonCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    // TODO: edit these messages
    public static final String MESSAGE_USAGE = "L " + COMMAND_WORD + ": sample";

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted Lesson: %1$s";

    private final Code targetCode;
    private final Type targetType;

    /**
     * Creates a DeleteLessonCommand to delete the specified {@code Lesson}
     * @param code
     * @param type
     */
    public DeleteLessonCommand(Code code, Type type) {
        this.targetCode = code;
        this.targetType = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Lesson> requestedLesson = model.getLesson(targetCode, targetType);

        if (requestedLesson.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_LESSON_DOES_NOT_EXIST);
        }

        Lesson lessonToDelete = requestedLesson.get();
        model.deleteLesson(lessonToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLessonCommand // instanceof handles nulls
                && targetCode.equals(((DeleteLessonCommand) other).targetCode)
                && targetType.equals(((DeleteLessonCommand) other).targetType)); // state check
    }
}
