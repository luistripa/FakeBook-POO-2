package users;

import exceptions.UserAlreadyExistsException;
import exceptions.UserDoesNotExistException;

import exceptions.UserHasNoFriendsException;
import exceptions.UsersAreAlreadyFriendsException;
import users.comparators.ComparatorAlphabetical;

import java.util.*;

public class UserCollectionClass implements UserCollection {

    private Map<String, User> users;

    public UserCollectionClass() {
        users = new HashMap<>();
        users.put("Hello", new UserClass("Hello", UserKind.FANATIC));
        users.put("world", new UserClass("world", UserKind.FANATIC));
        users.put("ana", new UserClass("ana", UserKind.FANATIC));
    }

    public boolean hasUser(String userID) {
        return users.containsKey(userID);

    }

    public void addUser(String userID, UserKind userKind) throws UserAlreadyExistsException {
        if (hasUser(userID))
            throw new UserAlreadyExistsException(userID);
        User user = new UserClass(userID, userKind);
        users.put(userID, user);
    }

    public Iterator<User> iterator() { // Sorted Alphabetically
        List<User> list = new LinkedList<>(users.values());
        list.sort(new ComparatorAlphabetical());
        return list.iterator();
    }

    public void addFriend(String u1_ID, String u2_ID) throws UserDoesNotExistException, UsersAreAlreadyFriendsException {
        User user1 = users.get(u1_ID);
        User user2 = users.get(u2_ID);
        if (user1 == null) {
            if (user2 == null)
                throw new UserDoesNotExistException(u2_ID);
            throw new UserDoesNotExistException(u1_ID);
        }
        user1.addFriend(user2);
        user2.addFriend(user1);
    }

    public Iterator<User> friendIterator(String userID) throws UserDoesNotExistException, UserHasNoFriendsException {
        User user = users.get(userID);
        if (user == null)
            throw new UserDoesNotExistException(userID);
        return user.friendIterator();
    }




}
