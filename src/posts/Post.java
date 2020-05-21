package posts;

import java.util.Iterator;
import java.util.List;

import comments.Comment;
import users.User;

public interface Post {

	int getPostID();
	
	User getAuthor();
	
	PostKind getKind();
	
	int getCommentCount();
	
	List<String> getHashtags();
	
	Iterator<Comment> commentThread();
	
	String getPostContent();

    boolean isHonest();

    void comment(Comment comment);
}
