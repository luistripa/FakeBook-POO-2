package users;

import exceptions.UserAlreadyExistsException;

import java.util.Iterator;

public interface UserCollection {

    void addUser(String userID, UserKind userKind) throws UserAlreadyExistsException;

    Iterator<User> iterator();
}
