package posts;

import comments.Comment;
import hashtags.HashTag;
import users.User;

import java.util.*;

public class PostClass implements Post {

    private int postID;
    private User author;
    private PostKind kind;
    private Map<String, HashTag> hashtags;
    private SortedMap<String, Comment> commentThread;
    private String postText;

    public PostClass(int postID, User author, PostKind kind, HashTag hashtags, Comment commentThread, String postText) {
        this.postID = postID;
        this.author = author;
        this.kind = kind;
        this.hashtags = new HashMap<String, HashTag>();
        this.commentThread = new TreeMap<>();
        this.postText = postText;
    }

    public int compareTo(Post other) {
        return 0;
    }
}
