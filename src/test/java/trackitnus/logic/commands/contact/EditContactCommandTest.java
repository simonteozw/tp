package trackitnus.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackitnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackitnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackitnus.testutil.TypicalPersons.getTypicalTrackIter;

import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.commons.core.index.Index;
import trackitnus.model.Model;
import trackitnus.model.ModelManager;
import trackitnus.model.TrackIter;
import trackitnus.model.UserPrefs;
import trackitnus.model.contact.Contact;
import trackitnus.testutil.EditContactDescriptorBuilder;
import trackitnus.testutil.PersonBuilder;
import trackitnus.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for EditContactCommand.
 */
public class EditContactCommandTest {

    private final Model model = new ModelManager(getTypicalTrackIter(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Contact editedContact = new PersonBuilder().build();
        EditContactCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder(editedContact).build();
        EditContactCommand editContactCommand = new EditContactCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditContactCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new TrackIter(model.getTrackIter()), new UserPrefs());
        expectedModel.setContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredContactList().size());
        Contact lastContact = model.getFilteredContactList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastContact);
        Contact editedContact =
            personInList.withName(ContactCommandTestUtil.VALID_NAME_BOB)
                .withPhone(ContactCommandTestUtil.VALID_PHONE_BOB)
                .withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND).build();

        EditContactCommand.EditContactDescriptor descriptor =
            new EditContactDescriptorBuilder().withName(ContactCommandTestUtil.VALID_NAME_BOB)
                .withPhone(ContactCommandTestUtil.VALID_PHONE_BOB).withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        EditContactCommand editContactCommand = new EditContactCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditContactCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new TrackIter(model.getTrackIter()), new UserPrefs());
        expectedModel.setContact(lastContact, editedContact);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditContactCommand editContactCommand = new EditContactCommand(TypicalIndexes.INDEX_FIRST_PERSON,
            new EditContactCommand.EditContactDescriptor());
        Contact editedContact = model.getFilteredContactList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditContactCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new TrackIter(model.getTrackIter()), new UserPrefs());

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        ContactCommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Contact contactInFilteredList =
            model.getFilteredContactList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Contact editedContact =
            new PersonBuilder(contactInFilteredList).withName(ContactCommandTestUtil.VALID_NAME_BOB).build();
        EditContactCommand editContactCommand = new EditContactCommand(TypicalIndexes.INDEX_FIRST_PERSON,
            new EditContactDescriptorBuilder().withName(ContactCommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditContactCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new TrackIter(model.getTrackIter()), new UserPrefs());
        expectedModel.setContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Contact firstContact = model.getFilteredContactList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        EditContactCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder(firstContact).build();
        EditContactCommand editContactCommand = new EditContactCommand(TypicalIndexes.INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editContactCommand, model, EditContactCommand.MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        ContactCommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        // edit contact in filtered list into a duplicate in address book
        Contact contactInList =
            model.getTrackIter().getContactList().get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased());
        EditContactCommand editContactCommand = new EditContactCommand(TypicalIndexes.INDEX_FIRST_PERSON,
            new EditContactDescriptorBuilder(contactInList).build());

        assertCommandFailure(editContactCommand, model, EditContactCommand.MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        EditContactCommand.EditContactDescriptor descriptor =
            new EditContactDescriptorBuilder().withName(ContactCommandTestUtil.VALID_NAME_BOB).build();
        EditContactCommand editContactCommand = new EditContactCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        ContactCommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTrackIter().getContactList().size());

        EditContactCommand editContactCommand = new EditContactCommand(outOfBoundIndex,
            new EditContactDescriptorBuilder().withName(ContactCommandTestUtil.VALID_NAME_BOB).build());

        assertCommandFailure(editContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditContactCommand standardCommand = new EditContactCommand(TypicalIndexes.INDEX_FIRST_PERSON,
            ContactCommandTestUtil.DESC_AMY);

        // same values -> returns true
        EditContactCommand.EditContactDescriptor copyDescriptor =
            new EditContactCommand.EditContactDescriptor(ContactCommandTestUtil.DESC_AMY);
        EditContactCommand commandWithSameValues = new EditContactCommand(TypicalIndexes.INDEX_FIRST_PERSON,
            copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearContactCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditContactCommand(TypicalIndexes.INDEX_SECOND_PERSON,
            ContactCommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditContactCommand(TypicalIndexes.INDEX_FIRST_PERSON,
            ContactCommandTestUtil.DESC_BOB)));
    }

}
