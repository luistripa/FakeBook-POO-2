package comments;

import users.User;

public interface Comment {

	User getAuthor();
	
	CommentStance getStance();
	
	String getCommentContent();

	boolean hasTopic(String topic);
	
}
