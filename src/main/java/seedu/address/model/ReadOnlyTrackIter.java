package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.module.Module;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTrackIter {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Contact> getPersonList();

    /**
     * Returns an unmodifiable view of the modules list.
     * This list will not contain any duplicate modules.
     *
     * @return
     */
    ObservableList<Module> getModuleList();
}
