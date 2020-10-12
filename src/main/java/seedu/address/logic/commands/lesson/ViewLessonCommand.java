package seedu.address.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.Code;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Type;

public class ViewLessonCommand extends Command {
    public static final String COMMAND_WORD = "info";

    public static final String MESSAGE_USAGE = Lesson.TYPE + " " + COMMAND_WORD
        + ": Shows the details of a lesson. "
        + "Parameters: "
        + PREFIX_CODE + "MODULE_CODE "
        + PREFIX_TYPE + "TYPE\n"
        + "Example: " + Lesson.TYPE + " " + COMMAND_WORD + " "
        + PREFIX_CODE + "CS3233 "
        + PREFIX_TYPE + "lecture\n";

    private static final String MESSAGE_VIEW_MODULE_SUCCESS = "Here is the module you requested: %1$s";

    private final Code code;
    private final Type type;

    /**
     * Creates a ViewLessonCommand to view the specified {@code Lesson}
     *
     * @param code
     * @param type
     */
    public ViewLessonCommand(Code code, Type type) {
        this.code = code;
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Lesson> requestedLesson = model.getLesson(code, type);


        if (requestedLesson.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_LESSON_DOES_NOT_EXIST);
        }

        return new CommandResult(String.format(MESSAGE_VIEW_MODULE_SUCCESS, requestedLesson.get()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ViewLessonCommand // instanceof handles nulls
            && code.equals(((ViewLessonCommand) other).code)
            && type.equals(((ViewLessonCommand) other).type)); // state check
    }
}