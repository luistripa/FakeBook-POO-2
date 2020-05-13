package comments;

import users.User;

public class CommentClass implements Comment {

    private final User author;
    private final CommentStance stance;
    private final String commentContent;

    public CommentClass(User author, CommentStance stance, String commentContent) {
        this.author = author;
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
}
