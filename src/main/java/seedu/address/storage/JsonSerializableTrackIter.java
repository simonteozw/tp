package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTrackIter;
import seedu.address.model.TrackIter;
import seedu.address.model.contact.Contact;

/**
 * An Immutable TrackIter that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableTrackIter {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate contact(s).";

    private final List<JsonAdaptedContact> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrackIter} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTrackIter(@JsonProperty("persons") List<JsonAdaptedContact> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyTrackIter} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTrackIter}.
     */
    public JsonSerializableTrackIter(ReadOnlyTrackIter source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TrackIter} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TrackIter toModelType() throws IllegalValueException {
        TrackIter trackIter = new TrackIter();
        for (JsonAdaptedContact jsonAdaptedContact : persons) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (trackIter.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            trackIter.addContact(contact);
        }
        return trackIter;
    }

}
