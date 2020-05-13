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

    public Iterator<Comment> commentThread() {
        return commentThread.values().iterator();
    }

    public String getPostText() {
        return postText;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (PostClass) obj;
        return postID == other.getPostID();

    }

    public int compareTo(Post other) {
        return 0;
    }
    
}
