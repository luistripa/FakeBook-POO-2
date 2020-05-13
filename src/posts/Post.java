package posts;

import java.util.List;
import java.util.SortedMap;

import comments.Comment;
import users.User;

public interface Post {

	int getPostID();
	
	User getAuthor();
	
	PostKind getKind();
	
	int getCommentCount();
	
	List<String> getHashtags();
	
	SortedMap<String, Comment> getCommentThread();
	
	String getPostText();
	
	int compareTo(Post other);
	
}
