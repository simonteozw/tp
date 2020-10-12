package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyTrackIter;
import seedu.address.model.contact.Contact;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the TrackIter.
     *
     * @see seedu.address.model.Model#getTrackIter()
     */
    ReadOnlyTrackIter getTrackIter();

    /**
     * Returns an unmodifiable view of the filtered list of contacts
     */
    ObservableList<Contact> getFilteredContactList();

    /**
     * Returns an unmodifiable view of the filtered list of modules
     */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Returns an unmodifiable view of the filtered list of tasks
     */
    ObservableList<Task> getFilteredTaskList();
    /**
     * Returns the user prefs' address book file path.
     */
    Path getTrackIterFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
