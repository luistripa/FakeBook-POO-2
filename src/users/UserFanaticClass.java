package users;

import comments.CommentStance;
import posts.PostKind;

import java.util.List;

/**
 * 
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class UserFanaticClass extends UserClass implements UserFanatic {

    List<String> loves;
    List<String> hates;

    public UserFanaticClass(String userID, UserKind userKind, List<String> loves, List<String> hates) {
        super(userID, userKind);
        this.loves = loves;
        this.hates = hates;
    }

    @Override
    public boolean hasHateFor(String fanaticism) {
        return hates.contains(fanaticism);
    }

    @Override
    public boolean hasLoveFor(String fanaticism) {
        return loves.contains(fanaticism);
    }

    @Override
    public boolean canPost(List<String> hashtags, PostKind stance) {

        for (String fanaticism :
                hashtags) {
            if (this.hasHateFor(fanaticism))
                return stance != PostKind.HONEST;
            else if (this.hasLoveFor(fanaticism))
                return stance == PostKind.HONEST;
        }
        return true;
    }

    @Override
    public boolean canComment(List<String> hashtags, PostKind stance, CommentStance commentStance) {

        for (String fanaticism :
                hashtags) {
            if (this.hasHateFor(fanaticism))
                if (stance == PostKind.HONEST)
                    return commentStance == CommentStance.NEGATIVE;
                else
                    return commentStance == CommentStance.POSITIVE;
            else if (this.hasLoveFor(fanaticism))
                if (stance == PostKind.HONEST)
                    return commentStance == CommentStance.POSITIVE;
                else
                    return commentStance == CommentStance.NEGATIVE;
        }
        return true;
    }

}
