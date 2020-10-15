package trackitnus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trackitnus.logic.commands.contact.ContactCommandTestUtil;
import trackitnus.model.contact.Contact;
import trackitnus.model.contact.exceptions.DuplicateContactException;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.module.Module;
import trackitnus.model.task.Task;
import trackitnus.testutil.Assert;
import trackitnus.testutil.PersonBuilder;
import trackitnus.testutil.TypicalPersons;

public class TrackIterTest {

    private final TrackIter trackIter = new TrackIter();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), trackIter.getContactList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> trackIter.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        TrackIter newData = TypicalPersons.getTypicalTrackIter();
        trackIter.resetData(newData);
        assertEquals(newData, trackIter);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two contacts with the same identity fields
        Contact editedAlice =
            new PersonBuilder(TypicalPersons.ALICE).withAddress(ContactCommandTestUtil.VALID_ADDRESS_BOB)
                .withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        List<Contact> newContacts = Arrays.asList(TypicalPersons.ALICE, editedAlice);
        TrackIterStub newData = new TrackIterStub(newContacts);

        Assert.assertThrows(DuplicateContactException.class, () -> trackIter.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> trackIter.hasContact(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(trackIter.hasContact(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        trackIter.addContact(TypicalPersons.ALICE);
        assertTrue(trackIter.hasContact(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        trackIter.addContact(TypicalPersons.ALICE);
        Contact editedAlice =
            new PersonBuilder(TypicalPersons.ALICE).withAddress(ContactCommandTestUtil.VALID_ADDRESS_BOB)
                .withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        assertTrue(trackIter.hasContact(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> trackIter.getContactList().remove(0));
    }

    /**
     * A stub ReadOnlyTrackIter whose contacts list can violate interface constraints.
     */
    private static class TrackIterStub implements ReadOnlyTrackIter {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
        private final ObservableList<Module> modules = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();
        private final ObservableList<Lesson> lessons = FXCollections.observableArrayList();

        TrackIterStub(Collection<Contact> contacts) {
            this.contacts.setAll(contacts);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ObservableList<Lesson> getLessonList() {
            return lessons;
        }
    }

}
