package comments;

import users.User;

import java.util.List;

public class CommentClass implements Comment {

    private final User author;
    private final CommentStance stance;
    private final List<String> topics;
    private final String commentContent;

    public CommentClass(User author, CommentStance stance, List<String> topics, String commentContent) {
        this.author = author;
        this.stance = stance;
        this.topics = topics;
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
    public boolean hasTopic(String topic) {
        return topics.contains(topic);
    }
}
