package comments;

import users.User;

public interface Comment {

	User getAuthor();
	
	CommentStance getStance();
	
	String getCommentContent();
	
}
