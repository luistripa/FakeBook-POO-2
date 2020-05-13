package users;

import comments.Comment;
import comments.CommentClass;
import comments.CommentStance;
import exceptions.UserHasNoFriendsException;
import exceptions.UserHasNoPostsException;
import exceptions.UsersAreAlreadyFriendsException;

import hashtags.HashTagClass;
import posts.Post;
import posts.PostClass;
import posts.PostKind;

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
        friends = new TreeMap<>();
        postsMade = new HashMap<>();
        postsReceived = new HashMap<>();
        commentsOnPosts = new HashMap<>();
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
    public Iterator<Post> postsIterator() throws UserHasNoPostsException{
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
        postsReceived.put(post.getPostID(), post);
    }


}
