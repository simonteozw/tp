package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTrackIter {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Contact> getContactList();

    /**
     * Returns an unmodifiable view of the modules list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<Module> getModuleList();

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

    /**
     * Returns an unmodifiable view of the lessons list.
     * This list will not contain any duplicate lessons.
     */
    ObservableList<Lesson> getLessonList();
}
