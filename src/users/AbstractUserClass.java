package users;

import comments.Comment;
import exceptions.UserHasNoFriendsException;
import exceptions.UsersAreAlreadyFriendsException;
import posts.Post;
import comparators.ComparatorAlphabetical;

import java.util.*;

public abstract class AbstractUserClass implements User {

    private String ID;
    private UserKind userKind;
    private Map<String, User> friends;
    private Map<Integer, Post> postsMade;
    private Map<Integer, Post> postsReceived;
    private Map<String, Comment> commentsOnPosts;

    public AbstractUserClass(String ID, UserKind userKind) {
        this.ID = ID;
        this.userKind = userKind;
        friends = new HashMap<>();
        postsMade = new HashMap<>();
        postsReceived = new HashMap<>();
        commentsOnPosts = new HashMap<>();
    }


    public String getID() {
        return ID;
    }

    public UserKind getUserKind() {
        return userKind;
    }

    public int getFriendCount() {
        return friends.size();
    }

    public int getPostsCount() {
        return postsMade.size();
    }

    public int getCommentsCount() {
        return commentsOnPosts.size();
    }

    public void addFriend(User user) throws UsersAreAlreadyFriendsException {
        if (friends.containsKey(user.getID()))
            throw new UsersAreAlreadyFriendsException(this.getID(), user.getID());
        friends.put(user.getID(), user);
    }

    public Iterator<User> friendIterator() throws UserHasNoFriendsException {
        if (getFriendCount() == 0)
            throw new UserHasNoFriendsException(this.getID());
        List<User> list = new LinkedList<>(friends.values());
        list.sort(new ComparatorAlphabetical());
        return list.iterator();
    }
}
