package comments;

import posts.Post;
import users.User;

public interface Comment {

	User getAuthor();

	Post getPost();
	
	CommentStance getStance();
	
	String getCommentContent();
	
	int getPostID();
	
	String getPostStance();

	String getPostAuthor();
	
}
