package trackitnus.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static trackitnus.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;
import static trackitnus.model.Model.PREDICATE_SHOW_ALL_LESSONS;

import java.util.Optional;
import java.util.function.Predicate;

import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.Model;
import trackitnus.model.commons.Code;
import trackitnus.model.lesson.Lesson;

public class ListLessonCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all lessons";
    public static final String MESSAGE_USAGE = Lesson.TYPE + " " + COMMAND_WORD
        + ": Lists all lessons of a module. "
        + "If MODULE_CODE is not specified, it will list all lessons of all modules instead.\n"
        + "Parameters: "
        + "[" + PREFIX_CODE + "MODULE_CODE]\n "
        + "Example: " + Lesson.TYPE + " " + COMMAND_WORD + " "
        + PREFIX_CODE + "CS3233\n";
    private final Optional<Code> codeOptional;

    /**
     * Creates a ListLessonCommand to list all {@code Lesson}s of the specified module
     *
     * @param codeOptional
     */
    public ListLessonCommand(Optional<Code> codeOptional) {
        this.codeOptional = codeOptional;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Predicate<Lesson> predicate;
        if (codeOptional.isEmpty()) {
            predicate = PREDICATE_SHOW_ALL_LESSONS;
        } else {
            if (!model.hasModule(codeOptional.get())) {
                throw new CommandException(MESSAGE_MODULE_DOES_NOT_EXIST);
            }
            predicate = lesson -> lesson.getCode().equals(codeOptional.get());
        }
        model.updateFilteredLessonList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
