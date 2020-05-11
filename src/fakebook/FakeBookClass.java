package fakebook;


import exceptions.*;
import users.*;

import java.util.*;

public class FakeBookClass implements FakeBook {

    private Map<String, User> users;

    public FakeBookClass() {
        users = new TreeMap<>();
    }


    public boolean hasUser(String userID) {
        return users.containsKey(userID);
    }

    public void addUser(String userID, UserKind userKind) throws UserAlreadyExistsException {
        User user;

        if (hasUser(userID))
            throw new UserAlreadyExistsException(userID);
        if (userKind == UserKind.FANATIC)
            user = new UserFanaticClass(userID, userKind);
        else
            user = new UserClass(userID, userKind);
        users.put(userID, user);
    }

    public void createNewFanaticism(String userID, String stance, String topic) throws InvalidFanaticismListException {
        UserFanatic user = (UserFanatic) users.get(userID);
        user.createNewFanaticism(stance, topic);
    }

    public Iterator<User> userIterator() throws NoUsersException {
        List<User> list = new ArrayList<>(users.values());
        Iterator<User> iter = list.iterator();

        if (iter.hasNext())
            return iter;
        throw new NoUsersException();
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

    public Iterator<User> userFriendIterator(String userID) throws UserDoesNotExistException, UserHasNoFriendsException {
        User user = users.get(userID);
        if (user == null)
            throw new UserDoesNotExistException(userID);
        return user.friendIterator();
    }
}
