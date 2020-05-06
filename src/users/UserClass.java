package users;

import comments.Comment;
import posts.Post;

import java.util.LinkedList;
import java.util.List;

public class UserClass implements User {

    private String ID;
    private UserKind userKind;
    private List<User> friends;
    private List<Post> postsMade;
    private List<Post> postsReceived;
    private List<Comment> commentsOnPosts;

    public UserClass(String ID, UserKind userKind) {
        this.ID = ID;
        this.userKind = userKind;
        friends = new LinkedList<User>();
        postsMade = new LinkedList<Post>();
        postsReceived = new LinkedList<Post>();
        commentsOnPosts = new LinkedList<Comment>();
    }


    public String getID() {
        return ID;
    }

    public UserKind getUserKind() {
        return userKind;
    }

    public int getFriendCount() {
        return friends.size();
    }

    public int getPostsCount() {
        return postsMade.size();
    }

    public int getCommentsCount() {
        return commentsOnPosts.size();
    }
}
