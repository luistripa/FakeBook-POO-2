package exceptions;

public class TopicNotFoundException extends Exception {

    public TopicNotFoundException(String topicID) {
        super("Oh please, who would write about "+topicID+"?");
    }
}
