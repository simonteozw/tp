package trackitnus.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import trackitnus.model.ReadOnlyTrackIter;
import trackitnus.model.TrackIter;
import trackitnus.model.commons.Name;
import trackitnus.model.contact.Contact;
import trackitnus.model.contact.Email;
import trackitnus.model.contact.Phone;
import trackitnus.model.tag.Tag;

/**
 * Contains utility methods for populating {@code TrackIter} with sample data.
 */
public class SampleDataUtil {
    public static Contact[] getSampleContacts() {
        return new Contact[]{
            new Contact(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getTagSet("friends")),
            new Contact(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends")),
            new Contact(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getTagSet("neighbours")),
            new Contact(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getTagSet("family")),
            new Contact(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                getTagSet("classmates")),
            new Contact(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyTrackIter getSampleTrackIter() {
        TrackIter sampleAb = new TrackIter();
        for (Contact sampleContact : getSampleContacts()) {
            sampleAb.addContact(sampleContact);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
