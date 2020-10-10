package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to TrackIt@NUS. "
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_DATE + "DATE "
        + PREFIX_ADDRESS + "ADDRESS "
        + PREFIX_WEIGHTAGE + "WEIGHTAGE "
        + PREFIX_REMARK + "REMARK\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "CS2103T Final "
        + PREFIX_DATE + "22/12/2020 "
        + PREFIX_ADDRESS + "COM2 NUS "
        + PREFIX_WEIGHTAGE + "50 "
        + PREFIX_REMARK + "Favourite module!";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";

    private final Task toAdd;

    /**
     * Creates an AddContactCommand to add the specified {@code Contact}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddTaskCommand // instanceof handles nulls
            && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
