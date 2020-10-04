package seedu.address.testutil;

import seedu.address.model.TrackIter;
import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code TrackIter ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private final TrackIter trackIter;

    public AddressBookBuilder() {
        trackIter = new TrackIter();
    }

    public AddressBookBuilder(TrackIter trackIter) {
        this.trackIter = trackIter;
    }

    /**
     * Adds a new {@code Contact} to the {@code TrackIter} that we are building.
     */
    public AddressBookBuilder withPerson(Contact contact) {
        trackIter.addContact(contact);
        return this;
    }

    public TrackIter build() {
        return trackIter;
    }
}
