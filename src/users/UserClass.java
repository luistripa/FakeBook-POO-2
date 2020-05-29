package users;

import comments.Comment;
import comments.CommentStance;
import exceptions.*;
import posts.Post;
import posts.PostKind;

import java.util.*;

/**
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class UserClass implements User {

    private final String ID;
    private final UserKind userKind;
    private final Map<String, User> friends;
    private final Map<Integer, Post> postsMade;
    private final List<Post> postsReceived;
    private final Map<String, List<Comment>> topics;
    private final Map<Post, Boolean> postRead;
    private int commentCount;
    private int postIDCounter;
    private Integer readPostNumber;
    private Integer numberOfLies;

    public UserClass(String ID, UserKind userKind) {
        this.ID = ID;
        this.userKind = userKind;
        friends = new TreeMap<>();
        postsMade = new HashMap<>();
        postsReceived = new ArrayList<>();
        topics = new HashMap<>();
        commentCount = 0;
        postIDCounter = 1;
        postRead = new HashMap<>();
        readPostNumber = 0;
        numberOfLies = 0;
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
    public Integer getPostsCount() {
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
    public int getReadPostNumber() {
        return readPostNumber;
    }

    @Override
    public Integer getTotalAccessiblePosts() {
        return postsMade.size() + postsReceived.size();
    }

    @Override
    public Double getResponsiveness() {
        return (double) readPostNumber / getTotalAccessiblePosts();
    }

    @Override
    public Integer getNumberOfLies() {
        return numberOfLies;
    }

    @Override
    public Post getPost(int postID) throws PostDoesNotExistException {
        Post post = postsMade.get(postID);
        if (post == null)
            throw new PostDoesNotExistException(this.getID(), postID);
        return post;
    }

    @Override
    public Post getReceivedPost(int postID) {
        for (Post post :
                postsReceived) {
            if (post.getID() == postID)
                return post;
        }
        return null;
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
    public void post(Post post) {
        postsMade.put(post.getID(), post);
        for (User friend:
                friends.values()) {
            friend.receivePost(post);
        }
        postRead.put(post, false);
        if (post.getKind() == PostKind.FAKE)
            numberOfLies++;
    }

    @Override
    public void receivePost(Post post) {
        postsReceived.add(post);
        postRead.put(post, false);
    }

    @Override
    public void comment(List<String> hashtags, Comment comment) {

        // Add the comment to all the necessary topics
        for (String hashtag :
                hashtags) {
            if (!topics.containsKey(hashtag))
                topics.put(hashtag, new ArrayList<>());
            topics.get(hashtag).add(comment);
        }
        commentCount++;

        // Update if post has not been read before
        if (!postRead.get(comment.getPost())) {
            postRead.put(comment.getPost(), true);
            readPostNumber++;
        }

        Post post = comment.getPost();

        if ( (post.getKind() == PostKind.FAKE && comment.getStance() == CommentStance.POSITIVE) || (post.getKind() == PostKind.HONEST && comment.getStance() == CommentStance.NEGATIVE) )
            numberOfLies++;
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
