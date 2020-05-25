package users;

import comments.Comment;
import exceptions.*;
import posts.Post;

import java.util.*;

public class UserClass implements User {

    private String ID;
    private UserKind userKind;
    private Map<String, User> friends;
    private Map<Integer, Post> postsMade;
    private List<Post> postsReceived;
    private Map<String, List<Comment>> topics;
    private int commentCount;
    private int postIDCounter;

    public UserClass(String ID, UserKind userKind) {
        this.ID = ID;
        this.userKind = userKind;
        friends = new TreeMap<>();
        postsMade = new HashMap<>();
        postsReceived = new ArrayList<>();
        topics = new HashMap<>();
        commentCount = 0;
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
        return commentCount;
    }

    @Override
    public int getPostIDCounter() {
        return postIDCounter++;
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
    public void post(Post post) {
        postsMade.put(post.getPostID(), post);
        for (User friend:
                friends.values()) {
            friend.receivePost(post);
        }
    }

    @Override
    public void receivePost(Post post) {
        postsReceived.add(post);
    }

    @Override
    public Post getReceivedPost(int postID) {
        for (Post post :
                postsReceived) {
            if (post.getPostID() == postID)
                return post;
        }
        return null;
    }

    @Override
    public void comment(List<String> hashtags, Comment comment) {
        for (String hashtag :
                hashtags) {
            if (!topics.containsKey(hashtag))
                topics.put(hashtag, new ArrayList<>());
            topics.get(hashtag).add(comment);
        }
        commentCount++;
    }

    @Override
    public Iterator<Comment> commentIterator(String topic) throws NoCommentsException {
    	if (getCommentsCount() == 0) {
    		throw new NoCommentsException();
    	}
    	List<Comment> listComments = topics.get(topic);
    	if (listComments == null)
    	    throw new NoCommentsException();
    	return listComments.iterator();
    }
}
