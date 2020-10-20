package trackitnus.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static trackitnus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_DATE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_TYPE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.Model;
import trackitnus.model.lesson.Lesson;

public class AddLessonCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = Lesson.TYPE + " " + COMMAND_WORD
        + ": Adds a lesson to TrackIt@NUS. "
        + "Parameters: "
        + PREFIX_CODE + "MODULE_CODE "
        + PREFIX_TYPE + "TYPE "
        + PREFIX_DATE + "DATE "
        + PREFIX_ADDRESS + "ADDRESS "
        + PREFIX_WEIGHTAGE + "WEIGHTAGE\n"
        + "Example: " + Lesson.TYPE + " " + COMMAND_WORD + " "
        + PREFIX_CODE + "CS3233 "
        + PREFIX_TYPE + "lecture "
        + PREFIX_DATE + "25/01/2021 "
        + PREFIX_ADDRESS + "COM1 PL2 "
        + PREFIX_WEIGHTAGE + "4\n";

    public static final String MESSAGE_SUCCESS = "New lesson added: %1$s";
    public static final String MESSAGE_DUPLICATE_LESSON = "This lesson already exists";

    private final Lesson toAdd;

    /**
     * Creates an AddLessonCommand to add the specified {@code Lesson}
     */
    public AddLessonCommand(Lesson lesson) {
        requireNonNull(lesson);
        toAdd = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasLesson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LESSON);
        }

        model.addLesson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddLessonCommand // instanceof handles nulls
            && toAdd.equals(((AddLessonCommand) other).toAdd));
    }
}
