package fakebook;


import exceptions.*;
import hashtags.HashTag;
import posts.Post;
import posts.PostClass;
import posts.PostKind;
import users.*;

import java.util.*;

public class FakeBookClass implements FakeBook {

    private Map<String, User> users;

    private int postIDCounter;

    public FakeBookClass() {
        users = new TreeMap<>();
        postIDCounter = 1;
    }


    public boolean hasUser(String userID) {
        return users.containsKey(userID);
    }

    @Override
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

    @Override
    public void createNewFanaticism(String userID, String stance, String topic) throws InvalidFanaticismListException {
        UserFanatic user = (UserFanatic) users.get(userID);
        user.createNewFanaticism(stance, topic);
    }

    @Override
    public Iterator<User> userIterator() throws NoUsersException {
        List<User> list = new ArrayList<>(users.values());
        Iterator<User> iter = list.iterator();

        if (iter.hasNext())
            return iter;
        throw new NoUsersException();
    }

    @Override
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

    @Override
    public Iterator<User> userFriendIterator(String userID) throws UserDoesNotExistException, UserHasNoFriendsException {
        User user = users.get(userID);
        if (user == null)
            throw new UserDoesNotExistException(userID);
        return user.friendIterator();
    }

    @Override
    public Iterator<Post> userPostIterator(String userID) throws UserDoesNotExistException, UserHasNoPostsException {  
    	User user = users.get(userID);
    	if (user == null)
    		 throw new UserDoesNotExistException(userID);
    	return user.postsIterator();
    }

    @Override
    public void post(String userID, List<String> hashtags, PostKind stance, String postContent) throws InadequateStanceException {
        User user = users.get(userID);
        UserKind userKind = user.getUserKind();

        // Check for inadequate stance
        if (userKind == UserKind.LIAR && stance == PostKind.HONEST)
            throw new InadequateStanceException();
        else if (userKind == UserKind.FANATIC && !fanaticUserCanPost(user, hashtags))
            throw new InadequateStanceException();

        // Create the post
        Post post = new PostClass(postIDCounter, user, stance, hashtags, postContent);

        user.post(post); // TODO: Implement method post(Post) in UserClass

    }



    /* Private Methods */

    private boolean fanaticUserCanPost(User user, List<String> hashtags) throws InadequateStanceException {
        UserFanatic userFanatic;
        if (user instanceof UserFanatic) {
            userFanatic = (UserFanatic) user;
            for (String fanaticism : hashtags)
                if (userFanatic.hasHateFor(fanaticism))
                    throw new InadequateStanceException();
        }
        return true;
    }
}
