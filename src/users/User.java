package users;

import comments.Comment;
import exceptions.NoCommentsException;
import exceptions.NoPostsException;
import exceptions.UserHasNoFriendsException;
import exceptions.UserHasNoPostsException;
import exceptions.UsersAreAlreadyFriendsException;
import posts.Post;
import posts.PostKind;

import java.util.Iterator;
import java.util.List;

public interface User {

    String getID();

    UserKind getUserKind();

    int getFriendCount();

    int getPostsCount();

    int getCommentsCount();

    int getPostIDCounter();

    void addFriend(User user) throws UsersAreAlreadyFriendsException;

    Iterator<User> friendIterator() throws UserHasNoFriendsException;
    
    Iterator<Post> postsIterator() throws UserHasNoPostsException;
    
    Post getPost(int postID);

    void post(Post post);

    void receivePost(Post post);

    Post getReceivedPost(int postID);

    void comment(List<String> hashtags, Comment comment);
    
    Iterator<Comment> commentIterator(String topic) throws NoCommentsException;
}
