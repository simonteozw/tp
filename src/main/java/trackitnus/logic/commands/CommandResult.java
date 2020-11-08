package trackitnus.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import trackitnus.model.module.Module;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Checks if user is editing a module
     */
    private final Module editedModule;

    /**
     * Checks if user is deleting a module
     */
    private final String nameOfDeletedModule;

    /**
     * If a user is editing a module, this is the module prior to it being edited
     */
    private final Module preEditedModule;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, Module editedModule,
                         Module preEditedModule,
                         String nameOfDeletedModule) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.editedModule = editedModule;
        this.preEditedModule = preEditedModule;
        this.nameOfDeletedModule = nameOfDeletedModule;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, null, null, "");
    }

    /**
     * Constructs a CommandResult to handle the event if a module is being edited
     * @param feedbackToUser of module being edited
     * @param editedModule the module it is being edited to
     * @param preEditedModule the module prior to being edited
     */
    public CommandResult(String feedbackToUser, Module editedModule, Module preEditedModule) {
        this(feedbackToUser, false, false, editedModule, preEditedModule, "");
    }

    /**
     * Constructs a CommandResult to handle the event of a module being deleted
     * @param feedbackToUser
     * @param nameOfDeletedModule
     */
    public CommandResult(String feedbackToUser, String nameOfDeletedModule) {
        this(feedbackToUser, false, false, null, null, nameOfDeletedModule);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public Module getEditedModule() {
        return editedModule;
    }

    public Module getPreEditedModule() {
        return preEditedModule;
    }

    public String getNameOfDeletedModule() {
        return nameOfDeletedModule;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
            && showHelp == otherCommandResult.showHelp
            && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
