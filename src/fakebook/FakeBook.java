package fakebook;

import posts.PostKind;
import users.UserKind;
import exceptions.*;
import hashtags.HashTag;
import posts.Post;
import users.*;

import java.util.Iterator;
import java.util.List;

import comments.Comment;

public interface FakeBook {

	boolean hasUser(String userID);
	
    void addUser(String userID, UserKind userKind) throws UserAlreadyExistsException;

    void createNewFanaticism(String userID, String stance, String topic) throws InvalidFanaticismListException;

    Iterator<User> userIterator() throws NoUsersException;

    void addFriend(String u1_ID, String u2_ID) throws UserDoesNotExistException, UsersAreAlreadyFriendsException;

    Iterator<User> userFriendIterator(String userID) throws UserDoesNotExistException, UserHasNoFriendsException;
    
    Iterator<Post> userPostIterator(String userID) throws UserDoesNotExistException, UserHasNoPostsException;

    void post(String userID, List<String> hashtags, PostKind stance, String postContent) throws InadequateStanceException;
    
    String getPostContent(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException;
    
    Iterator<Comment> postCommentsIterator(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException, NoCommentsException;
    
    PostKind getPostKind(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException;
    
}
