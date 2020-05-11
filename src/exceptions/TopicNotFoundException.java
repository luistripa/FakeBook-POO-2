package exceptions;

public class TopicNotFoundException extends Exception {

    public static final long serialVersionUID = 1L;

    public TopicNotFoundException(String topicID) {
        super("Oh please, who would write about "+topicID+"?");
    }
}
