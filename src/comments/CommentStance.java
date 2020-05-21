package comments;

public enum CommentStance {

    POSITIVE("positive"),
    NEGATIVE("negative");

    private String string;

    CommentStance(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
