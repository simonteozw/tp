package trackitnus.logic.commands.contact;

import static trackitnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackitnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackitnus.testutil.TypicalPersons.getTypicalTrackIter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackitnus.model.Model;
import trackitnus.model.ModelManager;
import trackitnus.model.UserPrefs;
import trackitnus.model.contact.Contact;
import trackitnus.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddContactCommand}.
 */
public class AddContactCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTrackIter(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Contact validContact = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getTrackIter(), new UserPrefs());
        expectedModel.addContact(validContact);

        assertCommandSuccess(new AddContactCommand(validContact), model,
            String.format(AddContactCommand.MESSAGE_SUCCESS, validContact), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Contact contactInList = model.getTrackIter().getContactList().get(0);
        assertCommandFailure(new AddContactCommand(contactInList), model, AddContactCommand.MESSAGE_DUPLICATE_CONTACT);
    }

}
