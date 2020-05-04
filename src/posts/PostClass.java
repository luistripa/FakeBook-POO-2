package posts;

import comments.Comment;
import hashtags.HashTag;
import users.User;

public class PostClass implements Post {

    private int postID;
    private User author;
    private PostKind kind;
    private HashTag hashtags;
    private Comment commentThread;
    private String postText;

    public PostClass(int postID, User author, PostKind kind, HashTag hashtags, Comment commentThread, String postText) {
        this.postID = postID;
        this.author = author;
        this.kind = kind;
        this.hashtags = hashtags;
        this.commentThread = commentThread;
        this.postText = postText;
    }
}
