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
import seedu.address.model.task.Task;

/**
 * An Immutable TrackIter that is serializable to JSON format.gi
 */
@JsonRootName(value = "addressbook")
class JsonSerializableTrackIter {

    public static final String MESSAGE_DUPLICATE_CONTACT = "Contact list contains duplicate contact(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Task list contains duplicate task(s).";

    private final List<JsonAdaptedContact> contacts = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrackIter} with the given contacts.
     */
    @JsonCreator
    public JsonSerializableTrackIter(@JsonProperty("contacts") List<JsonAdaptedContact> contacts,
                                     @JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.contacts.addAll(contacts);
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyTrackIter} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTrackIter}.
     */
    public JsonSerializableTrackIter(ReadOnlyTrackIter source) {
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TrackIter} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TrackIter toModelType() throws IllegalValueException {
        TrackIter trackIter = new TrackIter();
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (trackIter.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONTACT);
            }
            trackIter.addContact(contact);
        }
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (trackIter.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            trackIter.addTask(task);
        }
        return trackIter;
    }

}
