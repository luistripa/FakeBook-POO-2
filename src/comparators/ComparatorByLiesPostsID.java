package comparators;

import users.User;

import java.util.Comparator;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class ComparatorByLiesPostsID implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {
        int compLies = u1.getNumberOfLies().compareTo(u2.getNumberOfLies());

        if (compLies == 0) {

            int compPostNumber = u1.getPostsCount().compareTo(u2.getPostsCount());

            if (compPostNumber == 0)
                return u1.getID().compareTo(u2.getID());
            else
                return compPostNumber;
        } else
            return -compLies;
    }
}
