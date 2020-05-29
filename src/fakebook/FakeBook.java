package fakebook;

import users.*;
import posts.*;
import comments.*;
import exceptions.*;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public interface FakeBook {

	/**
	 * Method that checks if a certain user already exists.
	 *
	 * @param userID The user ID
	 */
	boolean hasUser(String userID);

	/**
	 * Method that adds a user to the social network.
	 *
	 * @param userID The userID
	 * @param userKind The user kind
	 * @throws UserAlreadyExistsException if the user already exists
	 */
    void addUser(String userID, UserKind userKind) throws UserAlreadyExistsException;

	/**
	 * Method that add a fanatic user to the social network.
	 *
	 * @param userID The user ID
	 * @param userKind The user kind
	 * @param loves The loves list
	 * @param hates The hates list
	 * @throws UserAlreadyExistsException if the user already exists
	 */
    void addUser(String userID, UserKind userKind, List<String> loves, List<String> hates) throws UserAlreadyExistsException;

    /**
     * Creates a new iterator of users.
	 *
     * @return An Iterator Object
     * @throws NoUsersException if there are no users on FakeBook
     */
    Iterator<User> userIterator() throws NoUsersException;

    /**
     * Method that sets a friendship between the two given users. The friendship is bi-directional.
	 *
     * @param u1_ID The first user ID
     * @param u2_ID The second user ID
     * @throws UserDoesNotExistException if any of the first or second user does not exist
     * @throws UsersAreAlreadyFriendsException if both users are already friends
     * @throws UserCannotFriendItselfException if a user attempted to friend itself
     */
    void addFriend(String u1_ID, String u2_ID) throws UserDoesNotExistException, UsersAreAlreadyFriendsException, UserCannotFriendItselfException;

    /**
     * Creates a new iterator of friends of a specific user.
	 *
     * @param userID The user ID
     * @return Iterator Object
     * @throws UserDoesNotExistException if the user does not exist
     * @throws UserHasNoFriendsException if the user has not friends
     */
    Iterator<User> userFriendIterator(String userID) throws UserDoesNotExistException, UserHasNoFriendsException;
    
    /**
     * Creates a new iterator of the posts from the specified user.
	 *
     * @param userID The user ID
     * @return Iterator Object
     * @throws UserDoesNotExistException if the user does not exist
     * @throws UserHasNoPostsException if the user has no posts
     */
    Iterator<Post> userPostIterator(String userID) throws UserDoesNotExistException, UserHasNoPostsException;

    /**
     * Method that allows a user to make a post in the social network.
	 *
     * @param userID of the user who is going to post.
     * @param hashtags related to that post.
     * @param stance The stance of the given post (honest or fake)
     * @param postContent  The content of the post
     * @return The ID of the newly created post.
     * @throws UserDoesNotExistException if the user does not exist
     * @throws InadequateStanceException if the post has an inadequate stance relative to the user kind
     */
    int post(String userID, List<String> hashtags, PostKind stance, String postContent) throws UserDoesNotExistException, InadequateStanceException;
    
    /**
     * Getter method for the content of the given post.
	 *
     * @param userID whom the post belongs to.
     * @param postID The post ID
     * @return the post content.
     * @throws UserDoesNotExistException if the user does not exist
     * @throws PostDoesNotExistException if the user does not have a post with given ID
     */
    String getPostContent(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException;
    
    /**
     * Creates a new iterator of  comments of the post with the given ID.
	 *
     * @param userID whom the post belongs.
     * @param postID The post ID
     * @return An iterator of comments
     * @throws UserDoesNotExistException if the user does not exist
     * @throws PostDoesNotExistException if the user does not have a post with given ID
     * @throws NoCommentsException if the post has no comments
     */
    Iterator<Comment> postCommentsIterator(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException, NoCommentsException;
    
    /**
     * Getter method for the kind of the post.
	 *
     * @param userID whom the post belongs.
     * @param postID The post ID
     * @return PostKind object containing the post's kind
     * @throws UserDoesNotExistException if the user who authored the post does not exist
     * @throws PostDoesNotExistException if the user does not have a post with given ID
     */
    PostKind getPostKind(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException;

    /**
     * Getter method for the number of friends of the given user.
	 *
     * @param userID The user ID
     * @return the number of friends.
     * @throws UserDoesNotExistException if the user does not exist
     */
    int getUserFriendCount(String userID) throws UserDoesNotExistException;

    /**
     * Method that allows the user to make a comment in a certain post.
	 *
     * @param userID represents the user who is going to comment.
     * @param authorID represents the user who made the post.
     * @param postID is the post where the comment will be made.
     * @param commentStance is the kind of comment (positive or negative).
     * @param commentText the comment text what will be written
     * @throws UserDoesNotExistException if both user or author do not exist
     * @throws UserHasNoAccessToPostException if the user who is going to comment does not have access to the post (not friends).
     * @throws PostDoesNotExistException if the post does not exist
     * @throws CannotCommentOnPostException if the user cannot comment on this post (opposing fanaticisms)
     * @throws InvalidCommentStanceException if the comment stance is invalid relative to the user kind
     */
    void comment(String userID, String authorID, int postID, CommentStance commentStance, String commentText) throws UserDoesNotExistException, UserHasNoAccessToPostException, PostDoesNotExistException, CannotCommentOnPostException, InvalidCommentStanceException; 
    
    /**
     * Creates a new iterator of the comments made by a user with the given ID and on the given topic.
	 *
     * @param userID the user ID
     * @param topic the topic to search comments on
     * @return An iterator of comments
     * @throws NoCommentsException if there are no comments on the given topic
     * @throws UserDoesNotExistException if the user does not exist
     */
    Iterator<Comment> commentsByUser(String userID, String topic) throws NoCommentsException, UserDoesNotExistException;

    /**
     * Creates a new iterator of users who are fanatic over the given topic.
	 *
     * @param topic The topic ID
     * @return Iterator of users
     * @throws FanaticismNotFoundException if the given topic is not found
     */
    Iterator<User> topicFanatics(String topic) throws FanaticismNotFoundException;

    /**
     * Creates a new iterator of posts which have the given topic.
	 *
     * @param topic the topic ID
     * @return An iterator of posts
     * @throws TopicNotFoundException if the topic is not found
     */
    Iterator<Post> topicPosts(String topic) throws TopicNotFoundException;

    /**
     * Method that gives the most popular post, in other words, the most commented post.
	 *
     * @return A post object representing the most commented post
     * @throws NoPostsException if there are no posts on FakeBook
     */
	Post popularPost() throws NoPostsException;

	/**
	 * Method that gives the top poster, in other words, the user who made more posts.
	 *
	 * @return A user object representing the top poster
	 * @throws NoTopPosterException if there is no top poster. Also means that there are no posts made on FakeBook yet
	 */
	User topPoster() throws NoTopPosterException;


	/**
	 * Method that shows the user who is the top liar, in other words, the one who has posted more lies, either by reinforcing a fake news or by giving
	 * a negative comment to an honest post.
	 *
	 * @return A user object representing the shamless user
	 * @throws NoShamelessUsersException if there is no shameless user on FakeBook
	 */
	User shameless() throws NoShamelessUsersException;

	/**
	 * Method that gives the user with the highest percentage of commented posts.
	 *
	 * @return A user object representing the most responsive user
	 * @throws NoResponsiveUsersException if there is no responsive user
	 */
    User responsive() throws NoResponsiveUsersException;
}
