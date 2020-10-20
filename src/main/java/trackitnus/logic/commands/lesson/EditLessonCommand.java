package trackitnus.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static trackitnus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_DATE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_TYPE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;
import static trackitnus.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.time.LocalDate;
import java.util.Optional;

import trackitnus.commons.core.Messages;
import trackitnus.commons.util.CollectionUtil;
import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.Model;
import trackitnus.model.commons.Address;
import trackitnus.model.commons.Code;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.lesson.Type;

public class EditLessonCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = Lesson.TYPE + " " + COMMAND_WORD
        + ": Edits the details of a lesson."
        + " At least one of the details has to be specified. "
        + "Parameters: "
        + PREFIX_CODE + "MODULE_CODE "
        + PREFIX_TYPE + "TYPE "
        + "[" + PREFIX_DATE + "DATE] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "[" + PREFIX_WEIGHTAGE + "WEIGHTAGE]\n"
        + "Example: " + Lesson.TYPE + " " + COMMAND_WORD + " "
        + PREFIX_CODE + "CS3233 "
        + PREFIX_TYPE + "lecture "
        + PREFIX_DATE + "27/01/2021 "
        + PREFIX_ADDRESS + "COM1 PL5 "
        + PREFIX_WEIGHTAGE + "3.5\n";

    public static final String MESSAGE_EDIT_LESSON_SUCCESS = "Edited Lesson: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_LESSON = "This lesson already exists.";

    private final Code code;
    private final Type type;
    private final EditLessonDescriptor editLessonDescriptor;

    /**
     * Creates a EditLessonCommand to edit the specified {@code Lesson}
     *
     * @param code
     * @param type
     * @param editLessonDescriptor
     */
    public EditLessonCommand(Code code, Type type, EditLessonDescriptor editLessonDescriptor) {
        requireNonNull(code);
        requireNonNull(type);
        requireNonNull(editLessonDescriptor);

        this.code = code;
        this.type = type;
        this.editLessonDescriptor = new EditLessonDescriptor(editLessonDescriptor);
    }

    /**
     * Creates and returns a {@code Contact} with the details of {@code contactToEdit}
     * edited with {@code editContactDescriptor}.
     */
    private static Lesson createEditedLesson(Lesson lessonToEdit, EditLessonDescriptor editLessonDescriptor) {
        assert lessonToEdit != null;

        Code originalCode = lessonToEdit.getCode();
        Type originalType = lessonToEdit.getType();
        LocalDate updatedDate = editLessonDescriptor.getDate().orElse(lessonToEdit.getDate());
        Address updatedAddress = editLessonDescriptor.getAddress().orElse(lessonToEdit.getAddress());
        Double updatedWeightage = editLessonDescriptor.getWeightage().orElse(lessonToEdit.getWeightage());

        return new Lesson(originalCode, originalType, updatedDate, updatedAddress, updatedWeightage);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!editLessonDescriptor.isAnyFieldEdited()) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        Optional<Lesson> lessonToEditOptional = model.getLesson(code, type);

        if (lessonToEditOptional.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_LESSON_DOES_NOT_EXIST);
        }

        Lesson lessonToEdit = lessonToEditOptional.get();
        Lesson editedLesson = createEditedLesson(lessonToEdit, editLessonDescriptor);

        if (!lessonToEdit.isSameLesson(editedLesson) && model.hasLesson(editedLesson)) {
            throw new CommandException(MESSAGE_DUPLICATE_LESSON);
        }

        model.setLesson(lessonToEdit, editedLesson);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(String.format(MESSAGE_EDIT_LESSON_SUCCESS, editedLesson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLessonCommand)) {
            return false;
        }

        // state check
        EditLessonCommand e = (EditLessonCommand) other;
        return code.equals(e.code)
            && type.equals(e.type)
            && editLessonDescriptor.equals(e.editLessonDescriptor);
    }

    /**
     * Stores the details to edit the contact with. Each non-empty field value will replace the
     * corresponding field value of the contact.
     */
    public static class EditLessonDescriptor {
        private Code code;
        private Type type;
        private LocalDate date;
        private Address address;
        private Double weightage;

        public EditLessonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditLessonDescriptor(EditLessonDescriptor toCopy) {
            setCode(toCopy.code);
            setType(toCopy.type);
            setDate(toCopy.date);
            setAddress(toCopy.address);
            setWeightage(toCopy.weightage);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, address, weightage);
        }

        public Optional<Code> getCode() {
            return Optional.ofNullable(code);
        }

        public void setCode(Code code) {
            this.code = code;
        }

        public Optional<Type> getType() {
            return Optional.ofNullable(type);
        }

        public void setType(Type type) {
            this.type = type;
        }

        public Optional<LocalDate> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Double> getWeightage() {
            return Optional.ofNullable(weightage);
        }

        public void setWeightage(Double weightage) {
            this.weightage = weightage;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditLessonDescriptor)) {
                return false;
            }

            // state check
            EditLessonDescriptor e = (EditLessonDescriptor) other;

            return getCode().equals(e.getCode())
                && getType().equals(e.getType())
                && getDate().equals(e.getDate())
                && getAddress().equals(e.getAddress())
                && getWeightage().equals(e.getWeightage());
        }
    }
}
