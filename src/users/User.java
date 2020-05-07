package users;

import exceptions.UserHasNoFriendsException;
import exceptions.UsersAreAlreadyFriendsException;

import java.util.Iterator;

public interface User {

    String getID();

    UserKind getUserKind();

    int getFriendCount();

    int getPostsCount();

    int getCommentsCount();

    void addFriend(User user) throws UsersAreAlreadyFriendsException;

    Iterator<User> friendIterator() throws UserHasNoFriendsException;

}
