package posts;

import comments.Comment;
import users.User;

import java.util.*;

public class PostClass implements Post {

    private int postID;
    private User author;
    private PostKind kind;
    private List<String> hashtags;
    private List<Comment> commentThread;
    private String postContent;

    public PostClass(int postID, User author, PostKind kind, List<String> hashtags, String postContent) {
        this.postID = postID;
        this.author = author;
        this.kind = kind;
        this.hashtags = new ArrayList<>(hashtags);
        this.commentThread = new ArrayList<>();
        this.postContent = postContent;
    }

    @Override
    public int getPostID() {
        return postID;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    @Override
    public PostKind getKind() {
        return kind;
    }
    
    @Override
    public int getCommentCount() {
    	return commentThread.size();
    }

    @Override
    public List<String> getHashtags() {
        return hashtags;
    }

    @Override
    public Iterator<Comment> commentThread() {
        return commentThread.iterator();
    }

    @Override
    public String getPostContent() {
        return postContent;
    }

    @Override
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

    @Override
    public int compareTo(Post other) {
        return 0;
    }
    
}
