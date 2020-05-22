package fakebook;

import comments.CommentStance;
import exceptions.*;
import posts.*;
import users.*;

import java.util.Iterator;
import java.util.List;

import comments.Comment;

public interface FakeBook {

	boolean hasUser(String userID);

    void addUser(String userID, UserKind userKind) throws UserAlreadyExistsException;

    void addUser(String userID, UserKind userKind, List<String> loves, List<String> hates) throws UserAlreadyExistsException;

    Iterator<User> userIterator() throws NoUsersException;

    void addFriend(String u1_ID, String u2_ID) throws UserDoesNotExistException, UsersAreAlreadyFriendsException, UserCannotFriendItselfException;

    Iterator<User> userFriendIterator(String userID) throws UserDoesNotExistException, UserHasNoFriendsException;
    
    Iterator<Post> userPostIterator(String userID) throws UserDoesNotExistException, UserHasNoPostsException;

    int post(String userID, List<String> hashtags, PostKind stance, String postContent) throws UserDoesNotExistException, InadequateStanceException;
    
    String getPostContent(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException;
    
    Iterator<Comment> postCommentsIterator(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException, NoCommentsException;
    
    PostKind getPostKind(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException;

    int getUserFriendCount(String userID) throws UserDoesNotExistException;

    void comment(String userID, String authorID, int postID, CommentStance commentStance, String commentText) throws UserDoesNotExistException, UserHasNoAccessToPostException, PostDoesNotExistException, CannotCommentOnPostException, InvalidCommentStanceException; 
    
    Iterator<Comment> commentsByUser(String userID, String topic) throws NoCommentsException, UserDoesNotExistException;

    Iterator<User> topicFanatics(String topic) throws FanaticismNotFoundException;

    Iterator<Post> topicPosts(String topic) throws TopicNotFoundException;
}
