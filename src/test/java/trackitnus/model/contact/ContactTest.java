package trackitnus.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackitnus.testutil.TypicalPersons.ALICE;
import static trackitnus.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import trackitnus.logic.commands.contact.ContactCommandTestUtil;
import trackitnus.testutil.Assert;
import trackitnus.testutil.PersonBuilder;

public class ContactTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Contact contact = new PersonBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> contact.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameContact(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameContact(null));

        // different phone and email -> returns false
        Contact editedAlice =
            new PersonBuilder(ALICE).withPhone(ContactCommandTestUtil.VALID_PHONE_BOB)
                .withEmail(ContactCommandTestUtil.VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // different name -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(ContactCommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice =
            new PersonBuilder(ALICE).withEmail(ContactCommandTestUtil.VALID_EMAIL_BOB)
                .withAddress(ContactCommandTestUtil.VALID_ADDRESS_BOB)
                .withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameContact(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice =
            new PersonBuilder(ALICE).withPhone(ContactCommandTestUtil.VALID_PHONE_BOB)
                .withAddress(ContactCommandTestUtil.VALID_ADDRESS_BOB)
                .withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameContact(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice =
            new PersonBuilder(ALICE).withAddress(ContactCommandTestUtil.VALID_ADDRESS_BOB)
                .withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameContact(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Contact aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different contact -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Contact editedAlice = new PersonBuilder(ALICE).withName(ContactCommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(ContactCommandTestUtil.VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(ContactCommandTestUtil.VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(ContactCommandTestUtil.VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
