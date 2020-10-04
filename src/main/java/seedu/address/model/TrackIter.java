package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TrackIter implements ReadOnlyTrackIter {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public TrackIter() {
    }

    /**
     * Creates an TrackIter using the Persons in the {@code toBeCopied}
     */
    public TrackIter(ReadOnlyTrackIter toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the contact list with {@code contacts}.
     * {@code contacts} must not contain duplicate contacts.
     */
    public void setPersons(List<Contact> contacts) {
        this.persons.setPersons(contacts);
    }

    /**
     * Resets the existing data of this {@code TrackIter} with {@code newData}.
     */
    public void resetData(ReadOnlyTrackIter newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// contact-level operations

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the address book.
     */
    public boolean hasPerson(Contact contact) {
        requireNonNull(contact);
        return persons.contains(contact);
    }

    /**
     * Adds a contact to the address book.
     * The contact must not already exist in the address book.
     */
    public void addPerson(Contact p) {
        persons.add(p);
    }

    /**
     * Replaces the given contact {@code target} in the list with {@code editedContact}.
     * {@code target} must exist in the address book.
     * The contact identity of {@code editedContact} must not
     * be the same as another existing contact in the address book.
     */
    public void setPerson(Contact target, Contact editedContact) {
        requireNonNull(editedContact);

        persons.setPerson(target, editedContact);
    }

    /**
     * Removes {@code key} from this {@code TrackIter}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Contact key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Contact> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TrackIter // instanceof handles nulls
            && persons.equals(((TrackIter) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
