package comparators;

import users.User;

import java.util.Comparator;

/**
 * A comparator that sorts in alphabetical order.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class ComparatorAlphabetical implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {

        String u1_ID = u1.getID();
        return u1_ID.compareTo(u2.getID());
    }
}
