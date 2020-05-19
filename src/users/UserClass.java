package users;

import comments.Comment;
import exceptions.UserHasNoFriendsException;
import exceptions.UserHasNoPostsException;
import exceptions.UsersAreAlreadyFriendsException;
import posts.Post;
import posts.PostClass;
import posts.PostKind;

import java.util.*;

public class UserClass implements User {

    private String ID;
    private UserKind userKind;
    private Map<String, User> friends;
    private Map<Integer, Post> postsMade;
    private Map<Integer, Post> postsReceived;
    private List<Comment> commentsOnPosts;
    private int postIDCounter;

    public UserClass(String ID, UserKind userKind) {
        this.ID = ID;
        this.userKind = userKind;
        friends = new TreeMap<>();
        postsMade = new HashMap<>();
        postsReceived = new HashMap<>();
        commentsOnPosts = new ArrayList<>();
        postIDCounter = 1;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public UserKind getUserKind() {
        return userKind;
    }

    @Override
    public int getFriendCount() {
        return friends.size();
    }

    @Override
    public int getPostsCount() {
        return postsMade.size();
    }

    @Override
    public int getCommentsCount() {
        return commentsOnPosts.size();
    }

    @Override
    public void addFriend(User user) throws UsersAreAlreadyFriendsException {
        if (friends.containsKey(user.getID()))
            throw new UsersAreAlreadyFriendsException(this.getID(), user.getID());
        friends.put(user.getID(), user);
    }

    @Override
    public Iterator<User> friendIterator() throws UserHasNoFriendsException {
        if (getFriendCount() == 0)
            throw new UserHasNoFriendsException(this.getID());
        List<User> list = new ArrayList<>(friends.values());
        return list.iterator();
    }

    @Override
    public Iterator<Post> postsIterator() throws UserHasNoPostsException {
        if (getPostsCount() == 0) {
            throw new UserHasNoPostsException(this.getID());
        }
        List<Post> list = new ArrayList<>(postsMade.values());
        return list.iterator();
    }

    @Override
    public Post getPost(int postID) {
        return postsMade.get(postID);
    }

    @Override
    public int post(User user, PostKind stance, List<String> hashtags, String postContent) {
        Post post = new PostClass(postIDCounter, user, stance, hashtags, postContent);
        postsMade.put(post.getPostID(), post);
        for (User friend:
                friends.values()) {
            friend.receivePost(post);
        }
        return postIDCounter++;
    }

    @Override
    public void receivePost(Post post) {
        postsReceived.put(post.getPostID(), post);
    }

    @Override
    public Post getReceivedPost(int postID) {
        return postsReceived.get(postID);
    }

    @Override
    public void comment(Comment comment) {
        commentsOnPosts.add(comment);
    }


}
