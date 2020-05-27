package comparators;

import posts.Post;
import users.User;

import java.util.Comparator;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class ComparatorByCommentsAuthorID implements Comparator<Post> {

    @Override
    public int compare(Post p1, Post p2) {

        // Compare by number of comments
        int comment = p1.getCommentCount().compareTo(p2.getCommentCount());

        if (comment == 0) {
            // Compare by author ID
            User u1 = p1.getAuthor();
            User u2 = p2.getAuthor();
            int author = u1.getID().compareTo(u2.getID());

            if (author == 0) {
                // Compare by decreasing order of ID

                return -p1.getPostID().compareTo(p2.getPostID());
            } else
                return author;
        } else
            return -comment;
    }
}
