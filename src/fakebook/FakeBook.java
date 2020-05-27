package fakebook;

import comments.CommentStance;
import exceptions.*;
import posts.*;
import users.*;

import java.util.Iterator;
import java.util.List;

import comments.Comment;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public interface FakeBook {

	/**
	 * Method that checks if a certain user already exists.
	 * @param userID
	 */
	boolean hasUser(String userID);

	/**
	 * Method that adds a user to the social network.
	 * @param userID
	 * @param userKind
	 * @throws UserAlreadyExistsException
	 */
    void addUser(String userID, UserKind userKind) throws UserAlreadyExistsException;

    /**
	 * Method that adds a fanatic user to the social network.
	 * @param userID
	 * @param userKind
	 * @throws UserAlreadyExistsException
	 */
    void addUser(String userID, UserKind userKind, List<String> loves, List<String> hates) throws UserAlreadyExistsException;

    /**
     * Creates a new iterator of users.
     * @return the iterator.
     * @throws NoUsersException
     */
    Iterator<User> userIterator() throws NoUsersException;

    /**
     * Method that sets a friendship between the two given users.
     * @param u1_ID
     * @param u2_ID
     * @throws UserDoesNotExistException
     * @throws UsersAreAlreadyFriendsException
     * @throws UserCannotFriendItselfException
     */
    void addFriend(String u1_ID, String u2_ID) throws UserDoesNotExistException, UsersAreAlreadyFriendsException, UserCannotFriendItselfException;

    /**
     * Creates a new iterator of users, but only of the friends of the user with the given ID.
     * @param userID
     * @return the iterator.
     * @throws UserDoesNotExistException
     * @throws UserHasNoFriendsException
     */
    Iterator<User> userFriendIterator(String userID) throws UserDoesNotExistException, UserHasNoFriendsException;
    
    /**
     * Creates a new iterator of the posts of the user with the given ID.
     * @param userID
     * @return the iterator.
     * @throws UserDoesNotExistException
     * @throws UserHasNoPostsException
     */
    Iterator<Post> userPostIterator(String userID) throws UserDoesNotExistException, UserHasNoPostsException;

    /**
     * Method that allows a user to make a post in the social network.
     * @param userID of the user who is going to post.
     * @param hashtags related to that post.
     * @param stance is the kind of the post.
     * @param postContent 
     * @return the ID of the post.
     * @throws UserDoesNotExistException
     * @throws InadequateStanceException
     */
    int post(String userID, List<String> hashtags, PostKind stance, String postContent) throws UserDoesNotExistException, InadequateStanceException;
    
    /**
     * Getter method for the content of the given post.
     * @param userID whom the post belongs.
     * @param postID
     * @return the post content.
     * @throws UserDoesNotExistException
     * @throws PostDoesNotExistException
     */
    String getPostContent(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException;
    
    /**
     * Creates a new iterator of the comments of the post with the given ID.
     * @param userID whom the post belongs.
     * @param postID
     * @return the iterator.
     * @throws UserDoesNotExistException
     * @throws PostDoesNotExistException
     * @throws NoCommentsException
     */
    Iterator<Comment> postCommentsIterator(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException, NoCommentsException;
    
    /**
     * Getter method for the kind of the post.
     * @param userID whom the post belongs.
     * @param postID
     * @return the kind of the post.
     * @throws UserDoesNotExistException
     * @throws PostDoesNotExistException
     */
    PostKind getPostKind(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException;

    /**
     * Getter method for the number of friends of the given user.
     * @param userID
     * @return the number of friends.
     * @throws UserDoesNotExistException
     */
    int getUserFriendCount(String userID) throws UserDoesNotExistException;

    /**
     * Method that allows the user to make a comment in a certain post.
     * @param userID represents the user who is going to comment.
     * @param authorID represents the user who made the post.
     * @param postID is the post where the comment will be made.
     * @param commentStance is the kind of comment.
     * @param commentText
     * @throws UserDoesNotExistException
     * @throws UserHasNoAccessToPostException
     * @throws PostDoesNotExistException
     * @throws CannotCommentOnPostException
     * @throws InvalidCommentStanceException
     */
    void comment(String userID, String authorID, int postID, CommentStance commentStance, String commentText) throws UserDoesNotExistException, UserHasNoAccessToPostException, PostDoesNotExistException, CannotCommentOnPostException, InvalidCommentStanceException; 
    
    /**
     * Creates a new iterator of the comments made by the user with the given ID and on the given topic.
     * @param userID
     * @param topic
     * @return the iterator.
     * @throws NoCommentsException
     * @throws UserDoesNotExistException
     */
    Iterator<Comment> commentsByUser(String userID, String topic) throws NoCommentsException, UserDoesNotExistException;

    /**
     * Creates a new iterator of users who are fanatic over the given topic.
     * @param topic
     * @return the iterator.
     * @throws FanaticismNotFoundException
     */
    Iterator<User> topicFanatics(String topic) throws FanaticismNotFoundException;

    /**
     * Creates a new iterator of posts who have the given topic.
     * @param topic
     * @return the iterator.
     * @throws TopicNotFoundException
     */
    Iterator<Post> topicPosts(String topic) throws TopicNotFoundException;

    /**
     * Method that gives the most popular post, in other words, the most commented post. 
     * @return a post which match this conditions.
     * @throws NoPostsException
     */
	Post popularPost() throws NoPostsException;

	/**
	 * Method that gives the top poster, in other words, the user who made more posts.
	 * @return a user which match this conditions.
	 * @throws NoTopPosterException
	 */
	User topPoster() throws NoTopPosterException;

	/**
	 * Method that gives the user with the highest percentage of commented posts.
	 * @return a user which match this conditions.
	 * @throws NoShamelessPostsException
	 */
	User shameless() throws NoShamelessPostsException;

	/**
	 * Method that shows the user who is the top liar, in other words, the one who has posted more lies, either by reinforcing a fake news or by giving 
	 * a negative comment to an honest post.
	 * @return a user which match this conditions.
	 * @throws NoResponsivePostsException
	 */
    User responsive() throws NoResponsivePostsException;
}
