package trackitnus.logic.commands.contact;

import static trackitnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackitnus.testutil.typical.TypicalTrackIter.getTypicalTrackIter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackitnus.model.Model;
import trackitnus.model.ModelManager;
import trackitnus.model.UserPrefs;
import trackitnus.testutil.typical.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListContactCommand.
 */
public class ListContactCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTrackIter(), new UserPrefs());
        expectedModel = new ModelManager(model.getTrackIter(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListContactCommand(), model, ListContactCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        ContactCommandTestUtil.showContactAtIndex(model, TypicalIndexes.INDEX_FIRST);
        assertCommandSuccess(new ListContactCommand(), model, ListContactCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
