package comparators;

import users.User;

import java.util.Comparator;

public class ComparatorAlphabetical implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {
        // Compare alphabetically

        String u1_ID = u1.getID();
        return u1_ID.compareTo(u2.getID());
    }
}
