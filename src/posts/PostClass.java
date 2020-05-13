package posts;

import comments.Comment;
import users.User;

import java.util.*;

public class PostClass implements Post {

    private int postID;
    private User author;
    private PostKind kind;
    private List<String> hashtags;
    private SortedMap<String, Comment> commentThread;
    private String postText;

    public PostClass(int postID, User author, PostKind kind, List<String> hashtags, String postText) {
        this.postID = postID;
        this.author = author;
        this.kind = kind;
        this.hashtags = new ArrayList<String>();
        this.commentThread = new TreeMap<>();
        this.postText = postText;
    }

    public int getPostID() {
        return postID;
    }

    public User getAuthor() {
        return author;
    }

    public PostKind getKind() {
        return kind;
    }
    
    public int getCommentCount() {
    	return commentThread.size();
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public SortedMap<String, Comment> getCommentThread() {
        return commentThread;
    }

    public String getPostText() {
        return postText;
    }

    public int compareTo(Post other) {
        return 0;
    }
    
}
