package trackitnus.logic.commands.contact;

import static trackitnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackitnus.testutil.TypicalContacts.getTypicalTrackIter;

import org.junit.jupiter.api.Test;

import trackitnus.model.Model;
import trackitnus.model.ModelManager;
import trackitnus.model.TrackIter;
import trackitnus.model.UserPrefs;

public class ClearContactCommandTest {

    @Test
    public void execute_emptyTrackIter_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearContactCommand(), model, ClearContactCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTrackIter_success() {
        Model model = new ModelManager(getTypicalTrackIter(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTrackIter(), new UserPrefs());
        expectedModel.setTrackIter(new TrackIter());

        assertCommandSuccess(new ClearContactCommand(), model, ClearContactCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
