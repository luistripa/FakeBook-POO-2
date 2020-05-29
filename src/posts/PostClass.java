package posts;

import comments.Comment;
import users.User;

import java.util.*;

/**
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 */
public class PostClass implements Post {

    private final Integer postID;
    private final User author;
    private final PostKind kind;
    private final List<String> hashtags;
    private final List<Comment> commentThread;
    private final String postContent;

    public PostClass(Integer postID, User author, PostKind kind, List<String> hashtags, String postContent) {
        this.postID = postID;
        this.author = author;
        this.kind = kind;
        this.hashtags = new ArrayList<>(hashtags);
        this.commentThread = new ArrayList<>();
        this.postContent = postContent;
    }

    @Override
    public Integer getID() {
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
    public String getContent() {
        return postContent;
    }

    @Override
    public void comment(Comment comment) {
        commentThread.add(comment);
    }

}
