package users;

import exceptions.UserAlreadyExistsException;
import exceptions.UserDoesNotExistException;
import users.comparators.ComparatorAlphabetical;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UserCollectionClass implements UserCollection {

    private List<User> users;

    public UserCollectionClass() {
        users = new LinkedList<User>();
    }

    public boolean hasUser(String userID) {
        try {
            searchIndex(userID);
            return true;
        } catch (UserDoesNotExistException e) {
            return false;
        }
    }

    public void addUser(String userID, UserKind userKind) throws UserAlreadyExistsException {
        if (hasUser(userID))
            throw new UserAlreadyExistsException(userID);
        User user = new UserClass(userID, userKind);
        users.add(user);
    }

    public Iterator<User> iterator() {
        users.sort(new ComparatorAlphabetical());
        return users.iterator();
    }




    /* Private Methods */

    private int searchIndex(String userID) throws UserDoesNotExistException {
        Iterator<User> iter = users.iterator();
        int i = -1;
        boolean found = false;
        while (iter.hasNext() && !found) {
            User u = iter.next();
            if (u.getID().equals(userID)) {
                found = true;
            }
            i++;
        }
        if (i==-1)
            throw new UserDoesNotExistException(userID);
        return i;
    }
}
