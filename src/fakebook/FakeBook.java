package fakebook;

import users.UserKind;
import exceptions.*;
import hashtags.HashTag;
import users.*;

import java.util.Iterator;
import java.util.List;

public interface FakeBook {

    void addUser(String userID, UserKind userKind) throws UserAlreadyExistsException;

    void createNewFanaticism(String userID, String stance, String topic) throws InvalidFanaticismListException;

    Iterator<User> userIterator() throws NoUsersException;

    void addFriend(String u1_ID, String u2_ID) throws UserDoesNotExistException, UsersAreAlreadyFriendsException;

    Iterator<User> userFriendIterator(String userID) throws UserDoesNotExistException, UserHasNoFriendsException;
}
