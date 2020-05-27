package users;

import comments.Comment;
import exceptions.*;
import posts.Post;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public interface User {

	/**
	 * Getter method for the ID of the user.
	 * @return ID.
	 */
    String getID();

    /**
	 * Getter method for the kind of the user.
	 * @return userKind.
	 */
    UserKind getUserKind();

    /**
	 * Getter method for the number of friends of the user.
	 * @return the number of friends.
	 */
    int getFriendCount();

    /**
	 * Getter method for the number of posts made by the user.
	 * @return the number of posts made.
	 */
    Integer getPostsCount();

    /**
   	 * Getter method for the number of comments made by the user.
   	 * @return the number of comments.
   	 */
    int getCommentsCount();

    /**
     * Getter method for the number of the current post. It also increments the counter everytime this method is used.
     * @return postIDCounter.
     */
    int getPostIDCounter();

    /**
     * Getter method for the number of read posts of the user.
     * @return readPostNumber
     */
    int getReadPostNumber();
    
    /**
     * Getter method for the number of posts that can be accessed by the user (sums the number of posts made with the number of posts received).
     * @return number of accessible posts.
     */
    Integer getTotalAccessiblePosts();

    /**
     * Getter method for the responsiveness (relation between the number of posts read and the number of accessible posts.
     * @return responsiveness.
     */
    Double getResponsiveness();
    
    /**
     * Getter method for the number of lies made by this user.
     * @return numberOfLies.
     */
	Integer getNumberOfLies();

    /**
     * Method that sets a friendship between the user and another user.
     * @param user which is going to become friends with the current user.
     * @throws UsersAreAlreadyFriendsException
     */
    void addFriend(User user) throws UsersAreAlreadyFriendsException;

    /**
     * Creates a new iterator of users, but only of the friends of this user.
     * @return the iterator.
     * @throws UserHasNoFriendsException
     */
    Iterator<User> friendIterator() throws UserHasNoFriendsException;
    
    /**
     * Creates a new iterator of posts made by this user.
     * @return the iterator.
     * @throws UserHasNoPostsException
     */
    Iterator<Post> postsIterator() throws UserHasNoPostsException;
    
    /**
     * Getter method for the post with the given ID made by this user.
     * @param postID
     * @return the post with the given ID.
     */
    Post getPost(int postID);

    /**
     * Method that allows the user to make a post.
     * @param post
     */
    void post(Post post);

    /**
     * Method that allows the user to receive a post made by one of his/hers friends in the feed.
     * @param post
     */
    void receivePost(Post post);

    /**
     * Getter method for the post with the given ID received by this user.
     * @param postID
     * @return
     */
    Post getReceivedPost(int postID);

    /**
     * Method that allows the user to make a comment.
     * @param hashtags of the comment
     * @param comment
     */
    void comment(List<String> hashtags, Comment comment);
    
    /**
     * Creates a new iterator of comments related to this user on the given topic.
     * @param topic
     * @return the iterator.
     * @throws NoCommentsException
     */
    Iterator<Comment> commentIterator(String topic) throws NoCommentsException;

}
