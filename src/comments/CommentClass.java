package comments;

import users.User;

public class CommentClass {

    private final User author;
    private final CommentStance stance;
    private final String commentText;

    public CommentClass(User author, CommentStance stance, String commentText) {
        this.author = author;
        this.stance = stance;
        this.commentText = commentText;
    }

    public User getAuthor() {
        return author;
    }

    public CommentStance getStance() {
        return stance;
    }

    public String getCommentText() {
        return commentText;
    }
}
