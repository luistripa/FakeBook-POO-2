package fakebook;

import exceptions.NoUsersException;
import exceptions.UserDoesNotExistException;
import exceptions.UserHasNoFriendsException;
import exceptions.UsersAreAlreadyFriendsException;
import users.*;
import users.comparators.ComparatorAlphabetical;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FakeBookClass implements FakeBook {

    private UserCollection users;

    public FakeBookClass() {
        users = new UserCollectionClass();
    }



    public Iterator<User> userIterator() throws NoUsersException {
        Iterator<User> iter = users.iterator();
        if (iter.hasNext())
            return iter;
        throw new NoUsersException();
    }

    public void addFriend(String u1_ID, String u2_ID) throws UserDoesNotExistException, UsersAreAlreadyFriendsException {
        users.addFriend(u1_ID, u2_ID);
    }

    public Iterator<User> userFriendIterator(String userID) throws UserDoesNotExistException, UserHasNoFriendsException {
        return users.friendIterator(userID);
    }
}
