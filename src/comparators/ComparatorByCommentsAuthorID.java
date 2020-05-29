package comparators;

import posts.Post;
import users.User;

import java.util.Comparator;

/**
 * A comparator that sorts in reversed number of comments (descendent), followed by ID of the author, followed by reverse post ID (newer posts first)
 *
 * @author Luis Tripa
 * @author Raquel Melo 57706
 *
 */
public class ComparatorByCommentsAuthorID implements Comparator<Post> {

    @Override
    public int compare(Post p1, Post p2) {

        int comment = p1.getCommentCount().compareTo(p2.getCommentCount());

        if (comment == 0) {

            User u1 = p1.getAuthor();
            User u2 = p2.getAuthor();
            int author = u1.getID().compareTo(u2.getID());

            if (author == 0) {
                return -p1.getID().compareTo(p2.getID());
            } else
                return author;
        } else
            return -comment;
    }
}
