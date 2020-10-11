package seedu.address.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.time.LocalDate;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.Address;
import seedu.address.model.commons.Code;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Type;

public class EditLessonCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    // TODO: edit these messages
    public static final String MESSAGE_USAGE = "L " + COMMAND_WORD + ": sample";

    public static final String MESSAGE_EDIT_LESSON_SUCCESS = "Edited Lesson: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_LESSON = "This lesson already exists.";

    private final Code code;
    private final Type type;
    private final EditLessonDescriptor editLessonDescriptor;

    /**
     * Creates a EditLessonCommand to edit the specified {@code Lesson}
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

        Code updatedCode = editLessonDescriptor.getCode().orElse(lessonToEdit.getCode());
        Type updatedType = editLessonDescriptor.getType().orElse(lessonToEdit.getType());
        LocalDate updatedTime = editLessonDescriptor.getTime().orElse(lessonToEdit.getTime());
        Address updatedLocation = editLessonDescriptor.getLocation().orElse(lessonToEdit.getLocation());
        Double updatedWeightage = editLessonDescriptor.getWeightage().orElse(lessonToEdit.getWeightage());

        return new Lesson(updatedCode, updatedType, updatedTime, updatedLocation, updatedWeightage);
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
        private LocalDate time;
        private Address location;
        private double weightage;

        public EditLessonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditLessonDescriptor(EditLessonDescriptor toCopy) {
            setCode(toCopy.code);
            setType(toCopy.type);
            setTime(toCopy.time);
            setLocation(toCopy.location);
            setWeightage(toCopy.weightage);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(code, type, time, location, weightage);
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

        public Optional<LocalDate> getTime() {
            return Optional.ofNullable(time);
        }

        public void setTime(LocalDate time) {
            this.time = time;
        }

        public Optional<Address> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setLocation(Address location) {
            this.location = location;
        }

        public Optional<Double> getWeightage() {
            return Optional.ofNullable(weightage);
        }

        public void setWeightage(double weightage) {
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
                    && getTime().equals(e.getTime())
                    && getLocation().equals(e.getLocation())
                    && getWeightage().equals(e.getWeightage());
        }
    }
}
