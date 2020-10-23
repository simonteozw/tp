package trackitnus.testutil;

import static trackitnus.testutil.typical.TypicalContacts.getTypicalContacts;
import static trackitnus.testutil.typical.TypicalModule.getTypicalModules;

import trackitnus.model.TrackIter;
import trackitnus.model.contact.Contact;
import trackitnus.model.module.Module;

public class TypicalTrackIter {
    /**
     * Returns an {@code TrackIter} with all the typical contacts.
     */
    public static TrackIter getTypicalTrackIter() {
        TrackIter track = new TrackIter();
        for (Contact contact : getTypicalContacts()) {
            track.addContact(contact);
        }
        for (Module module : getTypicalModules()) {
            track.addModule(module);
        }
        return track;
    }
}
