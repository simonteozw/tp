package seedu.address.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LESSONS;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.commons.Code;
import seedu.address.model.lesson.Lesson;

public class ListLessonCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all lessons";
    public static final String MESSAGE_USAGE = Lesson.TYPE + " " + COMMAND_WORD
            + ": Lists all lessons of a module. "
            + "If MODULE_CODE is not specified, it will list all lessons of all modules instead. "
            + "Parameters: "
            + "[" + PREFIX_CODE + "MODULE_CODE]\n ";

    private final Optional<Code> codeOptional;

    /**
     * Creates a ListLessonCommand to list all {@code Lesson}s of the specified module
     * @param codeOptional
     */
    public ListLessonCommand(Optional<Code> codeOptional) {
        this.codeOptional = codeOptional;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Lesson> predicate;
        if (codeOptional.isEmpty()) {
            predicate = PREDICATE_SHOW_ALL_LESSONS;
        } else {
            predicate = lesson -> lesson.getCode().equals(codeOptional.get());
        }
        model.updateFilteredLessonList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
