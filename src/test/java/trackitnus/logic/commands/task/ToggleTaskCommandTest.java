package trackitnus.logic.commands.task;

import static trackitnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackitnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackitnus.testutil.typical.TypicalTrackIter.getTypicalTrackIter;

import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.commons.core.index.Index;
import trackitnus.model.Model;
import trackitnus.model.ModelManager;
import trackitnus.model.UserPrefs;
import trackitnus.model.task.Task;
import trackitnus.testutil.typical.TypicalIndexes;

public class ToggleTaskCommandTest {

    private final Model model = new ModelManager(getTypicalTrackIter(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task toggledTask = model.getFilteredTaskList()
            .get(TypicalIndexes.INDEX_FIRST.getZeroBased()).toggleDoneStatus();
        ToggleTaskCommand toggleTaskCommand = new ToggleTaskCommand(TypicalIndexes.INDEX_FIRST);

        String expectedMessage = String.format(ToggleTaskCommand.MESSAGE_TOGGLE_TASK_SUCCESS, toggledTask);

        ModelManager expectedModel = new ModelManager(model.getTrackIter(), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(0), toggledTask);

        assertCommandSuccess(toggleTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        ToggleTaskCommand toggleTaskCommand = new ToggleTaskCommand(outOfBoundIndex);

        assertCommandFailure(toggleTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }
}
