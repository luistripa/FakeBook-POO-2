package fakebook;


import comments.*;
import comparators.*;
import exceptions.*;
import posts.*;
import users.*;

import java.util.*;

public class FakeBookClass implements FakeBook {

    private final Map<String, User> users;
    private final Map<String, SortedSet<User>> topicFanatics;
    private final Map<String, List<Post>> topicPosts;
    private Post popularPost;
    private User topPoster;

    public FakeBookClass() {
        users = new TreeMap<>();
        topicFanatics = new HashMap<>();
        topicPosts = new HashMap<>();
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
        else
            user = new UserClass(userID, userKind);
        users.put(userID, user);
    }

    @Override
    public void addUser(String userID, UserKind userKind, List<String> loves, List<String> hates) throws UserAlreadyExistsException {
        User user;

        if (hasUser(userID))
            throw new UserAlreadyExistsException(userID);
        user = new UserFanaticClass(userID, userKind, loves, hates);
        users.put(userID, user);

        // Process loves fanaticisms
        for (String fanaticism :
                loves) {
            if (!topicFanatics.containsKey(fanaticism))
                topicFanatics.put(fanaticism, new TreeSet<>(new ComparatorAlphabetical()));
            topicFanatics.get(fanaticism).add(user);
        }

        // Process hates fanaticisms
        for (String fanaticism :
                hates) {
            if (!topicFanatics.containsKey(fanaticism))
                topicFanatics.put(fanaticism, new TreeSet<>(new ComparatorAlphabetical()));
            topicFanatics.get(fanaticism).add(user);
        }
    }

    @Override
    public Iterator<User> userIterator() throws NoUsersException {
        Iterator<User> iter = users.values().iterator();

        if (iter.hasNext())
            return iter;
        throw new NoUsersException();
    }

    @Override
    public void addFriend(String u1_ID, String u2_ID) throws UserDoesNotExistException, UsersAreAlreadyFriendsException, UserCannotFriendItselfException {
        User user1 = users.get(u1_ID);
        User user2 = users.get(u2_ID);

        if (user1 == null)
            throw new UserDoesNotExistException(u1_ID);
        if (user2 == null)
            throw new UserDoesNotExistException(u2_ID);
        if (user1 == user2)
            throw new UserCannotFriendItselfException(u1_ID, u2_ID);

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
        else if (userKind == UserKind.FANATIC &&  !fanaticUserCanPost(user, hashtags, stance))
            throw new InadequateStanceException();

        // Generate post object
        Post post = new PostClass(user.getPostIDCounter(), user, stance, hashtags, postContent);

        // Add the post to the topicPosts map
        for (String topic :
                hashtags) {
            if (!topicPosts.containsKey(topic))
                topicPosts.put(topic, new ArrayList<>());
            topicPosts.get(topic).add(post);
        }

        // Post to the user feed. This includes sending the posts to all his friends, if there is any.
        user.post(post);

        updateTopPoster(user);
        
        return post.getPostID();
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
        else if (userKind == UserKind.LIAR && (post.getKind()==PostKind.HONEST) == (commentStance == CommentStance.POSITIVE))
            //throw new CannotCommentOnPostException(userID);
            throw new InvalidCommentStanceException();

        // Check if user is fanatic and if he can post on the post
        if (userKind == UserKind.FANATIC) {
            if ( !fanaticUserCanPost(user, post.getHashtags(), post.getKind(), commentStance) )
                throw new InvalidCommentStanceException();
        }

        Comment comment = new CommentClass(user, post, commentStance, commentText);
        user.comment(post.getHashtags(), comment);
        post.comment(comment);

        updatePopularPost(post);
        updateTopPoster(user);
    }

    @Override
    public Iterator<Comment> commentsByUser(String userID, String topic) throws NoCommentsException, UserDoesNotExistException {
    	User user = users.get(userID);
    	
    	if (user == null) {
    		throw new UserDoesNotExistException(userID);
    	}
    	return user.commentIterator(topic);
    }

    @Override
    public Iterator<User> topicFanatics(String topic) throws FanaticismNotFoundException {
        if (!topicFanatics.containsKey(topic))
            throw new FanaticismNotFoundException(topic);
        return topicFanatics.get(topic).iterator();
    }

    @Override
    public Iterator<Post> topicPosts(String topic) throws TopicNotFoundException {
        if (!topicPosts.containsKey(topic))
            throw new TopicNotFoundException(topic);
        List<Post> tp = topicPosts.get(topic);
        tp.sort(new ComparatorByCommentsAuthorID());
        return tp.iterator();
    }

    @Override
    public Post popularPost() throws NoPostsException {
    	if (popularPost == null) {
            throw new NoPostsException();
        }

    	return popularPost;
    }
    
    @Override
    public User topPoster() throws NoTopPosterException {
    	if (topPoster == null) {
    		throw new NoTopPosterException();
    	}
    	
    	return topPoster;
    }
    
    /* Private Methods */

    private boolean fanaticUserCanPost(User user, List<String> hashtags, PostKind stance) {
        UserFanatic userFanatic;
        userFanatic = (UserFanatic) user;

        for (String fanaticism :
                hashtags) {
            if (userFanatic.hasHateFor(fanaticism))
                return stance != PostKind.HONEST;
            else if (userFanatic.hasLoveFor(fanaticism))
                return stance == PostKind.HONEST;
        }
        return true;
    }

    private boolean fanaticUserCanPost(User user, List<String> hashtags, PostKind postStance, CommentStance commentStance) {
        UserFanatic userFanatic;
        userFanatic = (UserFanatic) user;

        for (String fanaticism :
                hashtags) {
            if (userFanatic.hasHateFor(fanaticism))
                if (postStance == PostKind.HONEST)
                    return commentStance == CommentStance.NEGATIVE;
                else
                    return commentStance == CommentStance.POSITIVE;
            else if (userFanatic.hasLoveFor(fanaticism))
                if (postStance == PostKind.HONEST)
                    return commentStance == CommentStance.POSITIVE;
                else
                    return commentStance == CommentStance.NEGATIVE;
        }
        return true;
    }

    private void updatePopularPost(Post post) {
        if (popularPost == null)
            popularPost = post;
        else {
            int compComments = popularPost.getCommentCount().compareTo(post.getCommentCount());

            if (compComments == 0) {
                int compAuthor = popularPost.getAuthor().getID().compareTo(post.getAuthor().getID());
                if (compAuthor == 0) {
                    if (popularPost.getPostID() < post.getPostID())
                        popularPost = post;
                } else if (compAuthor > 0)
                    popularPost = post;
            } else if (compComments < 0)
                popularPost = post;
        }
    }
    
    private void updateTopPoster(User user) {
    	if (topPoster == null)
    		topPoster = user;
    	else {
    		boolean compPosts = (topPoster.getPostsCount() == (user.getPostsCount()));

    		if (compPosts) {
    			boolean compComments = (topPoster.getCommentsCount() == (user.getCommentsCount()));
    			if (compComments) {
    				int compAuthor = topPoster.getID().compareTo(user.getID());
    				if(compAuthor > 0)
    					topPoster = user;
    			} else if (topPoster.getCommentsCount() < (user.getCommentsCount()))
    				topPoster = user;
    		} else if (topPoster.getPostsCount() < (user.getPostsCount()))
    			topPoster = user;
    	}

    }
    	
 }
    

