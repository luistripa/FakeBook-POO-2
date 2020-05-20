package comments;

import users.User;

import java.util.List;

import posts.Post;

public class CommentClass implements Comment {

    private final User author;
    private final Post post;
    private final CommentStance stance;
    private final String commentContent;

    public CommentClass(User author, Post post, CommentStance stance, String commentContent) {
        this.author = author;
        this.post = post;
        this.stance = stance;
        this.commentContent = commentContent;
    }

    @Override
    public User getAuthor() {
        return author;
    }
    
    @Override
    public CommentStance getStance() {
        return stance;
    }

    @Override
    public String getCommentContent() {
        return commentContent;
    }
    
    @Override
    public int getPostID() {
    	return post.getPostID();
    }
    
    @Override
    public String getPostStance() {
    	return post.getKind().getString();
    }
    
    @Override
    public String getPostAuthor() {
    	return post.getAuthor().getID();
    }
}
