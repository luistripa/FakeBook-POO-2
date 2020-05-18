package fakebook;


import comments.CommentClass;
import comments.CommentStance;
import exceptions.*;
import hashtags.HashTag;
import posts.Post;
import posts.PostClass;
import posts.PostKind;
import users.*;

import java.util.*;

import comments.Comment;

public class FakeBookClass implements FakeBook {

    private Map<String, User> users;
    private Map<Integer, Post> posts;

    private int postIDCounter;

    public FakeBookClass() {
        users = new TreeMap<>();
        posts = new TreeMap<>();
        postIDCounter = 1;
    }

    @Override
    public boolean hasUser(String userID) {
        return users.containsKey(userID);
    }

    @Override
    public void addUser(String userID, UserKind userKind) throws UserAlreadyExistsException {
        User user;

        if (hasUser(userID))
            throw new UserAlreadyExistsException(userID);
        if (userKind == UserKind.FANATIC)
            user = new UserFanaticClass(userID, userKind);
        else
            user = new UserClass(userID, userKind);
        users.put(userID, user);
    }

    @Override
    public void createNewFanaticism(String userID, String stance, String topic) throws InvalidFanaticismListException {
        UserFanatic user = (UserFanatic) users.get(userID);
        user.createNewFanaticism(stance, topic);
    }

    @Override
    public Iterator<User> userIterator() throws NoUsersException {
        List<User> list = new ArrayList<>(users.values());
        Iterator<User> iter = list.iterator();

        if (iter.hasNext())
            return iter;
        throw new NoUsersException();
    }

    @Override
    public void addFriend(String u1_ID, String u2_ID) throws UserDoesNotExistException, UsersAreAlreadyFriendsException {
        User user1 = users.get(u1_ID);
        User user2 = users.get(u2_ID);
        if (user1 == null) {
            if (user2 == null)
                throw new UserDoesNotExistException(u2_ID);
            throw new UserDoesNotExistException(u1_ID);
        }
        user1.addFriend(user2);
        user2.addFriend(user1);
    }

    @Override
    public Iterator<User> userFriendIterator(String userID) throws UserDoesNotExistException, UserHasNoFriendsException {
        User user = users.get(userID);
        if (user == null)
            throw new UserDoesNotExistException(userID);
        return user.friendIterator();
    }

    @Override
    public Iterator<Post> userPostIterator(String userID) throws UserDoesNotExistException, UserHasNoPostsException {  
    	User user = users.get(userID);
    	if (user == null)
    		 throw new UserDoesNotExistException(userID);
    	return user.postsIterator();
    }

    @Override
    public int post(String userID, List<String> hashtags, PostKind stance, String postContent) throws UserDoesNotExistException, InadequateStanceException {
        User user = users.get(userID);
        if (user == null)
            throw new UserDoesNotExistException(userID);
        UserKind userKind = user.getUserKind();

        // Check for inadequate stance
        if (userKind == UserKind.LIAR && stance == PostKind.HONEST)
            throw new InadequateStanceException();
        else if (userKind == UserKind.FANATIC && !fanaticUserCanPost(user, hashtags))
            throw new InadequateStanceException();

        // Create the post
        Post post = new PostClass(postIDCounter, user, stance, hashtags, postContent);

        // Post to the user feed. This includes sending the posts to all his friends, if there is any.
        user.post(post);

        // Save the post in the database for easier access
        posts.put(post.getPostID(), post);

        return postIDCounter++;

    }

    @Override
    public String getPostContent(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException {
    	User user = users.get(userID);
    	if (user == null)
    		throw new UserDoesNotExistException(userID);
    	Post post = user.getPost(postID);
    	if (post == null)
    		throw new PostDoesNotExistException(userID, postID);
    	return post.getPostContent();
    }
    
    @Override
    public Iterator<Comment> postCommentsIterator(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException, NoCommentsException {
    	User user = users.get(userID);
    	if (user == null)
    		throw new UserDoesNotExistException(userID);
    	Post post = user.getPost(postID);
    	if (post == null)
    		throw new PostDoesNotExistException(userID, postID);
    	if (post.getCommentCount() == 0)
    		throw new NoCommentsException();
    	return post.commentThread();
    }
    
    @Override
    public PostKind getPostKind(String userID, int postID) throws UserDoesNotExistException, PostDoesNotExistException {
    	User user = users.get(userID);
    	if (user == null)
    		throw new UserDoesNotExistException(userID);
    	Post post = user.getPost(postID);
    	if (post == null)
    		throw new PostDoesNotExistException(userID, postID);
    	return post.getKind();
    }

    @Override
    public int getUserFriendCount(String userID) throws UserDoesNotExistException {
        User user = users.get(userID);
        if (user == null)
            throw new UserDoesNotExistException(userID);
        return user.getFriendCount();
    }

    @Override
    public void comment(String userID, String authorID, int postID, CommentStance commentStance, String commentText) throws UserDoesNotExistException, UserHasNoAccessToPostException, PostDoesNotExistException, CannotCommentOnPostException, InvalidCommentStanceException {
        User user = users.get(userID);
        User author = users.get(authorID);

        // Check if users exist
        if (user == null)
            throw new UserDoesNotExistException(userID);
        if (author == null)
            throw new UserDoesNotExistException(authorID);

        // Check if author has the post
        if (author.getPost(postID) == null)
            throw new PostDoesNotExistException(authorID, postID);

        // Check if user has access to the post (is a friend)
        Post post;
        if (author == user)
            post = author.getPost(postID);
        else {
            post = user.getReceivedPost(postID);
            if (post == null)
                throw new UserHasNoAccessToPostException(userID, postID, authorID);
        }

        // Check if user can post on the post
        UserKind userKind = user.getUserKind();
        if (userKind == UserKind.SELFCENTERED && post.getAuthor() != user)
            throw new CannotCommentOnPostException(userID);
        else if (userKind == UserKind.NAIVE && commentStance == CommentStance.NEGATIVE)
            throw new CannotCommentOnPostException(userID);
        else if (userKind == UserKind.LIAR && post.isHonest() == (commentStance == CommentStance.POSITIVE))
            throw new CannotCommentOnPostException(userID);

        // Check if user is fanatic and if he can post on the post
        UserFanatic userFanatic;
        if (userKind == UserKind.FANATIC) {
            userFanatic = (UserFanatic) user;
            boolean fanaticismPositive = userFanatic.isFanaticismPositive(post.getHashtags());
            if ( (post.getKind() == PostKind.HONEST) == (commentStance == CommentStance.POSITIVE ^ fanaticismPositive) )
                throw new InvalidCommentStanceException();
        }

        Comment comment = new CommentClass(author, commentStance, post.getHashtags(), commentText);
        user.comment(comment);
        post.comment(comment);
    }


    /* Private Methods */

    private boolean fanaticUserCanPost(User user, List<String> hashtags) throws InadequateStanceException {
        UserFanatic userFanatic;
        if (user instanceof UserFanatic) {
            userFanatic = (UserFanatic) user;
            for (String fanaticism : hashtags)
                if (userFanatic.hasHateFor(fanaticism))
                    throw new InadequateStanceException();
        }
        return true;
    }
}
