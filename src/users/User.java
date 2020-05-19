package users;

import comments.Comment;
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

    void addFriend(User user) throws UsersAreAlreadyFriendsException;

    Iterator<User> friendIterator() throws UserHasNoFriendsException;
    
    Iterator<Post> postsIterator() throws UserHasNoPostsException;
    
    Post getPost(int postID);

    int post(User user, PostKind stance, List<String> hashtags, String postContent);

    void receivePost(Post post);

    Post getReceivedPost(int postID);

    void comment(Comment comment);
}
