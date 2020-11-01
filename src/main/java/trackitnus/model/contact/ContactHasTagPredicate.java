package trackitnus.model.contact;

import java.util.function.Predicate;

import trackitnus.model.tag.Tag;

public class ContactHasTagPredicate implements Predicate<Contact> {

    private final Tag tag;

    public ContactHasTagPredicate(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Contact contact) {
        return contact.getTags().contains(tag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ContactHasTagPredicate // instanceof handles nulls
            && tag.equals(((ContactHasTagPredicate) other).tag)); // state check
    }
}
