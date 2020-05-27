package users;

import java.util.List;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
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

}
