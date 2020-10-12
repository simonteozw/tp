package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.Address;
import seedu.address.model.commons.Name;
import seedu.address.model.task.Task;

public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = Task.TYPE + " " + COMMAND_WORD
        + ": Edits the details of the task identified "
        + "by the index number used in the displayed contact list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_DATE + "DATE] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "[" + PREFIX_WEIGHTAGE + "WEIGHTAGE] "
        + "[" + PREFIX_REMARK + "REMARK]...\n"
        + "Example: " + Task.TYPE + " " + COMMAND_WORD + " 1 "
        + PREFIX_WEIGHTAGE + "25 "
        + PREFIX_REMARK + "New remark";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in TrackIt@NUS.";

    private final Index index;
    private final EditTaskCommand.EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index              of the task in the filtered contact list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskCommand.EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskCommand.EditTaskDescriptor(editTaskDescriptor);
    }

    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Name updatedName = editTaskDescriptor.getName().orElse(taskToEdit.getName());
        LocalDate updatedDate = editTaskDescriptor.getDate().orElse(taskToEdit.getDate());
        Address updatedAddress = editTaskDescriptor.getAddress().orElse(taskToEdit.getAddress());
        double updatedWeightage = editTaskDescriptor.getWeightage().orElse(taskToEdit.getWeightage());
        String updatedRemarks = editTaskDescriptor.getRemark().orElse(taskToEdit.getRemark());

        return new Task(updatedName, updatedDate, updatedAddress, updatedWeightage, updatedRemarks);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return index.equals(e.index)
            && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Name name;
        private LocalDate date;
        private Address address;
        private double weightage;
        private String remark;

        public EditTaskDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTaskDescriptor(EditTaskCommand.EditTaskDescriptor toCopy) {
            setName(toCopy.name);
            setDate(toCopy.date);
            setAddress(toCopy.address);
            setWeightage(toCopy.weightage);
            setRemark(toCopy.remark);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, date, address, weightage, remark);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<LocalDate> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Optional<Double> getWeightage() {
            return Optional.ofNullable(weightage);
        }

        public void setWeightage(double weightage) {
            this.weightage = weightage;
        }

        public Optional<String> getRemark() {
            return Optional.ofNullable(remark);
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskCommand.EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskCommand.EditTaskDescriptor e = (EditTaskCommand.EditTaskDescriptor) other;

            return getName().equals(e.getName())
                && getDate().equals(e.getDate())
                && getAddress().equals(e.getAddress())
                && getWeightage().equals(e.getWeightage())
                && getRemark().equals(e.getRemark());
        }
    }
}
