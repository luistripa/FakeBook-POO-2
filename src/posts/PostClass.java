package posts;

import comments.Comment;
import users.User;

import java.util.*;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
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
    public Integer getPostID() {
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
    public Integer getCommentCount() {
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
    public void comment(Comment comment) {
        commentThread.add(comment);
    }

}
