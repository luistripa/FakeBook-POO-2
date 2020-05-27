package exceptions;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class TopicNotFoundException extends Exception {

    public static final long serialVersionUID = 1L;

    public TopicNotFoundException(String topicID) {
        super("Oh please, who would write about "+topicID+"?");
    }
}
