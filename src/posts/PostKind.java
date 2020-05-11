package posts;

public enum PostKind {

    HONEST("honest"),
    FAKE("fake");

    private final String postType;

    PostKind(String message) {
        this.postType = message;
    }

    public String getString() {
        return postType;
    }
}
