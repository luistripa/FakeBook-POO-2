package fakebook;

import exceptions.NoUsersException;
import exceptions.UserDoesNotExistException;
import exceptions.UserHasNoFriendsException;
import exceptions.UsersAreAlreadyFriendsException;
import users.User;

import java.util.Iterator;

public interface FakeBook {


    Iterator<User> userIterator() throws NoUsersException;

    void addFriend(String u1_ID, String u2_ID) throws UserDoesNotExistException, UsersAreAlreadyFriendsException;

    Iterator<User> userFriendIterator(String userID) throws UserDoesNotExistException, UserHasNoFriendsException;
}
