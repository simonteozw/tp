package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;
import seedu.address.model.module.Module;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Contact> PREDICATE_SHOW_ALL_CONTACTS = unused -> true;
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' TrackIter file path.
     */
    Path getTrackIterFilePath();

    /**
     * Sets the user prefs' TrackIter file path.
     */
    void setTrackIterFilePath(Path trackIterFilePath);

    /**
     * Returns the TrackIter
     */
    ReadOnlyTrackIter getTrackIter();

    /**
     * Replaces TrackIter data with the data in {@code trackIt}.
     */
    void setTrackIter(ReadOnlyTrackIter trackIt);

    //=========== Contact ================================================================================

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the TrackIter.
     */
    boolean hasContact(Contact contact);

    /**
     * Deletes the given contact.
     * The contact must exist in the TrackIter.
     */
    void deleteContact(Contact target);

    /**
     * Adds the given contact.
     * {@code contact} must not already exist in the TrackIter.
     */
    void addContact(Contact contact);

    /**
     * Replaces the given contact {@code target} with {@code editedContact}.
     * {@code target} must exist in the TrackIter.
     * The contact identity of {@code editedContact} must not
     * be the same as another existing contact in the TrackIter.
     */
    void setContact(Contact target, Contact editedContact);

    /**
     * Returns an unmodifiable view of the filtered contact list
     */
    ObservableList<Contact> getFilteredContactList();

    /**
     * Updates the filter of the filtered contact list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredContactList(Predicate<Contact> predicate);

    //=========== Module ================================================================================

    /**
     * Returns true if a module with the same identity as {@code module} exists in the TrackIter.
     */
    boolean hasModule(Module module);

    /**
     * Deletes the given module.
     * The module must exist in the TrackIter.
     */
    void deleteModule(Module target);

    /**
     * Adds the given contact.
     * {@code module} must not already exist in the TrackIter.
     */
    void addModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in the TrackIter.
     * The module identity of {@code editedModule} must not
     * be the same as another existing module in the TrackIter.
     */
    void setModule(Module target, Module editedModule);

    /**
     * Returns an unmodifiable view of the filtered module list
     */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);
    
}
