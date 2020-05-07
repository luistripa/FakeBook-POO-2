package users;

import exceptions.UserAlreadyExistsException;
import exceptions.UserDoesNotExistException;
import exceptions.UserHasNoFriendsException;
import exceptions.UsersAreAlreadyFriendsException;

import java.util.Iterator;

public interface UserCollection {

    void addUser(String userID, UserKind userKind) throws UserAlreadyExistsException;

    Iterator<User> iterator();

    void addFriend(String u1_ID, String u2_ID) throws UserDoesNotExistException, UsersAreAlreadyFriendsException;

    Iterator<User> friendIterator(String userID) throws UserDoesNotExistException, UserHasNoFriendsException;
}
