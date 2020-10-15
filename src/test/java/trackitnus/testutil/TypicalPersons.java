package trackitnus.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackitnus.logic.commands.contact.ContactCommandTestUtil;
import trackitnus.model.TrackIter;
import trackitnus.model.contact.Contact;

/**
 * A utility class containing a list of {@code Contact} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Contact ALICE = new PersonBuilder().withName("Alice Pauline")
        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
        .withPhone("94351253")
        .withTags("friends").build();
    public static final Contact BENSON = new PersonBuilder().withName("Benson Meier")
        .withAddress("311, Clementi Ave 2, #02-25")
        .withEmail("johnd@example.com").withPhone("98765432")
        .withTags("owesMoney", "friends").build();
    public static final Contact CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
        .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Contact DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
        .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Contact ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
        .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Contact FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
        .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Contact GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
        .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Contact HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
        .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Contact IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
        .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Contact's details found in {@code CommandTestUtil}
    public static final Contact AMY =
        new PersonBuilder().withName(ContactCommandTestUtil.VALID_NAME_AMY)
            .withPhone(ContactCommandTestUtil.VALID_PHONE_AMY)
            .withEmail(ContactCommandTestUtil.VALID_EMAIL_AMY).withAddress(ContactCommandTestUtil.VALID_ADDRESS_AMY)
            .withTags(ContactCommandTestUtil.VALID_TAG_FRIEND).build();
    public static final Contact BOB =
        new PersonBuilder().withName(ContactCommandTestUtil.VALID_NAME_BOB)
            .withPhone(ContactCommandTestUtil.VALID_PHONE_BOB)
            .withEmail(ContactCommandTestUtil.VALID_EMAIL_BOB).withAddress(ContactCommandTestUtil.VALID_ADDRESS_BOB)
            .withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND, ContactCommandTestUtil.VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code TrackIter} with all the typical persons.
     */
    public static TrackIter getTypicalTrackIter() {
        TrackIter ab = new TrackIter();
        for (Contact contact : getTypicalPersons()) {
            ab.addContact(contact);
        }
        return ab;
    }

    public static List<Contact> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
