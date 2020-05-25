package users;

import comments.Comment;
import exceptions.*;
import posts.Post;

import java.util.Iterator;
import java.util.List;

public interface User {

    String getID();

    UserKind getUserKind();

    int getFriendCount();

    Integer getPostsCount();

    int getCommentsCount();

    int getPostIDCounter();

    int getReadPostNumber();

    Integer getTotalAccessiblePosts();

    Double getResponsiveness();

    void addFriend(User user) throws UsersAreAlreadyFriendsException;

    Iterator<User> friendIterator() throws UserHasNoFriendsException;
    
    Iterator<Post> postsIterator() throws UserHasNoPostsException;
    
    Post getPost(int postID);

    void post(Post post);

    void receivePost(Post post);

    Post getReceivedPost(int postID);

    void comment(List<String> hashtags, Comment comment);
    
    Iterator<Comment> commentIterator(String topic) throws NoCommentsException;

	Integer getNumberOfLies();
}
