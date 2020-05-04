package users;

import comments.Comment;
import posts.Post;

import java.util.LinkedList;
import java.util.List;

public class UserClass implements User {

    private String userID;
    private UserKind userKind;
    private List<User> friends;
    private List<Post> postsMade;
    private List<Post> postsReceived;
    private List<Comment> commentsOnPosts;

    public UserClass(String userID, UserKind userKind) {
        this.userID = userID;
        this.userKind = userKind;
        friends = new LinkedList<User>();
        postsMade = new LinkedList<Post>();
        postsReceived = new LinkedList<Post>();
        commentsOnPosts = new LinkedList<Comment>();
    }
}
