package users;

import comments.Comment;
import exceptions.UserHasNoFriendsException;
import exceptions.UserHasNoPostsException;
import exceptions.UsersAreAlreadyFriendsException;
import posts.Post;

import java.util.Iterator;

public interface User {

    String getID();

    UserKind getUserKind();

    int getFriendCount();

    int getPostsCount();

    int getCommentsCount();

    void addFriend(User user) throws UsersAreAlreadyFriendsException;

    Iterator<User> friendIterator() throws UserHasNoFriendsException;
    
    Iterator<Post> postsIterator() throws UserHasNoPostsException;
    
    Post getPost(int postID);

    void post(Post post);

    void receivePost(Post post);

    Post getReceivedPost(int postID);

    void comment(Comment comment);
}
